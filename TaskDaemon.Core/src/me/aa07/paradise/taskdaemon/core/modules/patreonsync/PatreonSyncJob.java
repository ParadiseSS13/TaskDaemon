package me.aa07.paradise.taskdaemon.core.modules.patreonsync;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import me.aa07.paradise.taskdaemon.core.config.PatreonConfig;
import me.aa07.paradise.taskdaemon.core.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.core.models.patreon.PatreonOuterResponseModel;
import me.aa07.paradise.taskdaemon.core.models.patreon.PatreonRawResponseModel;
import me.aa07.paradise.taskdaemon.core.models.patreon.PatreonUser;
import me.aa07.paradise.taskdaemon.core.util.UtilStr;
import me.aa07.paradise.taskdaemon.database.authentik.tables.records.AuthentikCoreUsersourceconnectionRecord;
import me.aa07.paradise.taskdaemon.database.automation.Tables;
import me.aa07.paradise.taskdaemon.database.automation.tables.records.PatreonSupportersRecord;
import me.aa07.paradise.taskdaemon.database.gamedb.tables.records.DonatorsRecord;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution // NO
public class PatreonSyncJob implements Job {
    /*

    - pull raw data from patreon
    - empty the holding table and fill with the patreon raw data
    - get authentik IDs and add into table
    - get discord IDs and add into table
    - get ckeys and add into table
    - mass sync ingame stuff

    */

    private static final UUID PATREON_OAUTH_SERVICE_ID = UUID.fromString("626f4935-d046-4957-9862-f26102e27a70");
    private static final UUID DISCORD_OAUTH_SERVICE_ID = UUID.fromString("bc9fb785-6a1f-434e-80da-26a37450a29a");
    private static final UUID BYOND_OAUTH_SERVICE_ID = UUID.fromString("d7a7b659-69bd-4435-b585-3b4ba97ca076");

    @Override
    public void execute(JobExecutionContext event) throws JobExecutionException {
        JobDataMap datamap = event.getMergedJobDataMap();

        // Get our logger - important
        Object raw_logger = datamap.get("LOGGER");
        Optional<Logger> logger_holder = Optional.empty();

        if (raw_logger instanceof Logger l2) {
            logger_holder = Optional.of(l2);
        }

        if (!logger_holder.isPresent()) {
            System.out.println("[PatreonSync] LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        Logger logger = logger_holder.get();
        logger.info("[PatreonSync] Starting up");

        // Now get our DB
        Object raw_db = datamap.get("DBCORE");
        Optional<DbCore> dbcore_holder = Optional.empty();

        if (raw_db instanceof DbCore db2) {
            dbcore_holder = Optional.of(db2);
        }

        if (!dbcore_holder.isPresent()) {
            logger.error("[PatreonSync] DBCORE WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        DbCore dbcore = dbcore_holder.get();
        dbcore.getClass();

        // Now get our config
        Object raw_ptrcfg = datamap.get("PTRCFG");
        Optional<PatreonConfig> ptrcfg_holder = Optional.empty();

        if (raw_ptrcfg instanceof PatreonConfig ptrcfg2) {
            ptrcfg_holder = Optional.of(ptrcfg2);
        }

        if (!ptrcfg_holder.isPresent()) {
            logger.error("[PatreonSync] PTRCFG WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        PatreonConfig ptrcfg = ptrcfg_holder.get();

        // So
        // The patreon API is kinda bad
        // Everything goes into the same loose model and you have to manually parse out members and tiers
        // Boy thats fun

        ArrayList<PatreonUser> users = new ArrayList<PatreonUser>();

        try {
            HttpRequest httpreq = HttpRequest.newBuilder()
                .uri(URI.create("https://www.patreon.com/api/oauth2/v2/campaigns/"
                + String.valueOf(ptrcfg.campaignId)
                + "/members"
                + "?include=currently_entitled_tiers,user"
                + "&fields[member]=patron_status,currently_entitled_amount_cents"
                + "&fields[tier]=amount_cents"
                + "&page[count]=1000")) // This will break if we get >1000 patrons
                // TODO Add pagination
                .GET()
                .header("Accept", "application/json")
                .header("Authorization", String.format("Bearer %s", ptrcfg.creatorToken))
                .header("User-Agent", "ParadiseSS13-TaskDaemon/1.0")
                .build();

            HttpClient client = HttpClient.newHttpClient();
            logger.info("[PatreonSync] Pulling data");
            HttpResponse<String> response = client.send(httpreq, BodyHandlers.ofString());

            Gson gson = new Gson();
            String response_body = response.body();

            /*

            // Uncomment if you need horrendous debugging

            File file = new File("patreon_debug_response.json");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(response_body);
            fw.close();
            */

            PatreonOuterResponseModel holder = gson.fromJson(response_body, PatreonOuterResponseModel.class);

            logger.info(String.format("[PatreonSync] Pulled %s raw models from Patreon", holder.data.size()));

            for (PatreonRawResponseModel raw_model : holder.data) {
                if (raw_model.type.equalsIgnoreCase("member")) {
                    // Make sure theyre valid first
                    if (raw_model.attributes != null && raw_model.attributes.patronStatus != null) {
                        if (raw_model.attributes.patronStatus.equalsIgnoreCase("active_patron")) {
                            PatreonUser user = raw_model.asUser();
                            users.add(user);
                            continue;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }

        logger.info(String.format("[PatreonSync] Loaded %s active users", users.size()));

        ArrayList<Integer> only_member_ids = new ArrayList<Integer>();

        for (PatreonUser pu : users) {
            only_member_ids.add(pu.userId);
        }

        // Step 1 - Clear out old
        DSLContext automation_jooq = dbcore.jooq(DatabaseType.Automation, SQLDialect.MYSQL);
        automation_jooq.deleteFrom(Tables.PATREON_SUPPORTERS)
            .where(Tables.PATREON_SUPPORTERS.MEMBER_ID.notIn(only_member_ids))
            .execute();

        logger.info("[PatreonSync] Cleaned out old users");

        int insert_count = 0;
        int update_count = 0;

        // Step 2 - Add or insert new
        for (PatreonUser pu : users) {
            if (automation_jooq.fetchExists(automation_jooq.selectFrom(Tables.PATREON_SUPPORTERS)
                .where(Tables.PATREON_SUPPORTERS.MEMBER_ID.eq(pu.userId)))) {

                update_count++;

                // Fetch and update our record
                PatreonSupportersRecord psr = automation_jooq.selectFrom(Tables.PATREON_SUPPORTERS)
                    .where(Tables.PATREON_SUPPORTERS.MEMBER_ID.eq(pu.userId))
                    .fetchSingle();

                psr.setAmountCents(pu.amountInCents);
                psr.setDonationTier(pu.maxTier().tierLevel);

                // And store
                psr.store();
            } else {
                insert_count++;

                // Make a new record
                PatreonSupportersRecord psr = automation_jooq.newRecord(Tables.PATREON_SUPPORTERS);
                psr.setMemberId(pu.userId);
                psr.setAmountCents(pu.amountInCents);
                psr.setDonationTier(pu.maxTier().tierLevel);

                // And store
                psr.store();
            }
        }

        logger.info(String.format("[PatreonSync] Inserted %s users, updated %s users", insert_count, update_count));


        DSLContext game_jooq = dbcore.jooq(DatabaseType.GameDb, SQLDialect.MYSQL);
        DSLContext authentik_jooq = dbcore.jooq(DatabaseType.Authentik, SQLDialect.POSTGRES);

        // Find authentik users
        HashMap<Integer, Integer> authentik_to_patreon = new HashMap<Integer, Integer>();
        findAuthentikUsers(only_member_ids, authentik_jooq, automation_jooq, logger, authentik_to_patreon);

        // Now find discord users
        findDiscordUsers(authentik_jooq, automation_jooq, logger, authentik_to_patreon);

        // Now get ckeys
        findCkeys(authentik_jooq, automation_jooq, logger, authentik_to_patreon);

        // And commit to game DB
        updateGameDb(automation_jooq, game_jooq, logger);

        System.gc(); // This is important after this one - the response model is like multiple megabytes
        logger.info("[PatreonSync] Process complete");
    }

    private static int patreonTier2ingameTier(int patreonTier) {
        switch (patreonTier) {
            case 0: // No tier
                return 0;
            case 1: // Supporter
                return 0;
            case 2: // Icon
                return 1;
            case 3: // Loadout
                return 2;
            case 4: // Silver
                return 3;
            case 5: // Gold
                return 4;
            default:
                return 0;
        }
    }

    private static void findAuthentikUsers(
        List<Integer> memberIds,
        DSLContext authentikJooq,
        DSLContext automationJooq,
        Logger logger,
        HashMap<Integer, Integer> authentik2patreon
    ) {
        for (int member_id : memberIds) {
            // This loop should only have active member IDs for the DB given we just flushed everything else out

            // Get ready for a TON of absolute pathing
            AuthentikCoreUsersourceconnectionRecord authentik_record = authentikJooq
                .selectFrom(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION)
                .where(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.IDENTIFIER.eq(String.valueOf(member_id)))
                .and(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.SOURCE_ID.eq(PATREON_OAUTH_SERVICE_ID))
                .fetchOne();

            if (authentik_record == null) {
                logger.warn(String.format("[PatreonSync] Patreon user ID %s not found in Authentik!", member_id));
                continue;
            }

            authentik2patreon.put(authentik_record.getUserId(), member_id);
        }

        logger.info(String.format("[PatreonSync] Loaded %s Authentik users", authentik2patreon.size()));

        int supporters_authentik_updated = 0;
        for (int authentik_id : authentik2patreon.keySet()) {
            int patreon_id = authentik2patreon.get(authentik_id);

            // Get our DB record

            PatreonSupportersRecord psr = automationJooq.selectFrom(Tables.PATREON_SUPPORTERS)
                .where(Tables.PATREON_SUPPORTERS.MEMBER_ID.eq(patreon_id))
                .fetchOne();

            if (psr == null) {
                logger.warn(String.format("[PatreonSync] Patreon user ID %s not found in supporters table. This shouldnt be possible!", patreon_id));
                continue;
            }

            supporters_authentik_updated++;
            psr.setAuthentikId(authentik_id);
            psr.store(); // Store it back in
        }

        logger.info(String.format("[PatreonSync] Updated %s rows with Authentik users", supporters_authentik_updated));
    }

    private static void findDiscordUsers(
        DSLContext authentikJooq,
        DSLContext automationJooq,
        Logger logger,
        HashMap<Integer, Integer> authentik2patreon
    ) {
        HashMap<Integer, Long> authentik2discord = new HashMap<Integer, Long>();

        for (int authentik_id : authentik2patreon.keySet()) {
            // This loop should only have active member IDs for the DB given we just flushed everything else out

            // Get ready for a TON of absolute pathing
            AuthentikCoreUsersourceconnectionRecord authentik_record = authentikJooq
                .selectFrom(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION)
                .where(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.USER_ID.eq(authentik_id))
                .and(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.SOURCE_ID.eq(DISCORD_OAUTH_SERVICE_ID))
                .fetchOne();

            if (authentik_record == null) {
                logger.warn(String.format("[PatreonSync] Couldnt find a discord link for Authentik user %s!", authentik_id));
                continue;
            }

            authentik2discord.put(authentik_id, Long.valueOf(authentik_record.getIdentifier()));
        }

        logger.info(String.format("[PatreonSync] Loaded %s Discord users", authentik2discord.size()));

        int supporters_updated = 0;
        for (int authentik_id : authentik2discord.keySet()) {
            if (!authentik2patreon.containsKey(authentik_id)) {
                logger.warn(String.format("[PatreonSync] Couldnt find a patreon user for Authentik user %s - this shouldnt be possible!", authentik_id));
            }

            int patreon_id = authentik2patreon.get(authentik_id);
            long discord_id = authentik2discord.get(authentik_id);

            // Get our DB record

            PatreonSupportersRecord psr = automationJooq.selectFrom(Tables.PATREON_SUPPORTERS)
                .where(Tables.PATREON_SUPPORTERS.MEMBER_ID.eq(patreon_id))
                .fetchOne();

            if (psr == null) {
                logger.warn(String.format("[PatreonSync] Patreon user ID %s not found in supporters table. This shouldnt be possible!", patreon_id));
                continue;
            }

            supporters_updated++;
            psr.setDiscordId(discord_id);
            psr.store(); // Store it back in
        }

        logger.info(String.format("[PatreonSync] Updated %s rows with Discord users", supporters_updated));
    }

    private static void findCkeys(
        DSLContext authentikJooq,
        DSLContext automationJooq,
        Logger logger,
        HashMap<Integer, Integer> authentik2patreon
    ) {
        HashMap<Integer, String> authentik2ckey = new HashMap<Integer, String>();

        for (int authentik_id : authentik2patreon.keySet()) {
            // This loop should only have active member IDs for the DB given we just flushed everything else out

            // Get ready for a TON of absolute pathing
            AuthentikCoreUsersourceconnectionRecord authentik_record = authentikJooq
                .selectFrom(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION)
                .where(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.USER_ID.eq(authentik_id))
                .and(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.SOURCE_ID.eq(BYOND_OAUTH_SERVICE_ID))
                .fetchOne();

            if (authentik_record == null) {
                logger.warn(String.format("[PatreonSync] Couldnt find a discord link for Authentik user %s!", authentik_id));
                continue;
            }

            // Normalise the ckey
            String ckey = UtilStr.cleanCkey(authentik_record.getIdentifier());

            authentik2ckey.put(authentik_id, ckey);
        }

        logger.info(String.format("[PatreonSync] Loaded %s ckeys", authentik2ckey.size()));

        int supporters_updated = 0;
        for (int authentik_id : authentik2ckey.keySet()) {
            if (!authentik2patreon.containsKey(authentik_id)) {
                logger.warn(String.format("[PatreonSync] Couldnt find a patreon user for Authentik user %s - this shouldnt be possible!", authentik_id));
            }

            int patreon_id = authentik2patreon.get(authentik_id);
            String ckey = authentik2ckey.get(authentik_id);

            // Get our DB record
            PatreonSupportersRecord psr = automationJooq.selectFrom(Tables.PATREON_SUPPORTERS)
                .where(Tables.PATREON_SUPPORTERS.MEMBER_ID.eq(patreon_id))
                .fetchOne();

            if (psr == null) {
                logger.warn(String.format("[PatreonSync] Patreon user ID %s not found in supporters table. This shouldnt be possible!", patreon_id));
                continue;
            }

            supporters_updated++;
            psr.setCkey(ckey);
            psr.store(); // Store it back in
        }

        logger.info(String.format("[PatreonSync] Updated %s rows with ckeys", supporters_updated));
    }

    private static void updateGameDb(DSLContext automationJooq, DSLContext gameJooq, Logger logger) {

        Result<PatreonSupportersRecord> valid_records = automationJooq.selectFrom(Tables.PATREON_SUPPORTERS)
            .where(Tables.PATREON_SUPPORTERS.CKEY.isNotNull())
            .and(Tables.PATREON_SUPPORTERS.DONATION_TIER.ge(2)) // Tier 2 or higher to appear ingame
            .fetch();

        logger.info(String.format("[PatreonSync] Loaded %s records to update in the Game DB", valid_records.size()));

        ArrayList<String> only_ckeys = new ArrayList<String>();

        for (PatreonSupportersRecord psr : valid_records) {
            only_ckeys.add(psr.getCkey());
        }

        // Remove from BYOND where not in this list
        gameJooq.deleteFrom(me.aa07.paradise.taskdaemon.database.gamedb.Tables.DONATORS)
            .where(me.aa07.paradise.taskdaemon.database.gamedb.Tables.DONATORS.CKEY.notIn(only_ckeys))
            .execute();

        // And where not active to keep the table short
        gameJooq.deleteFrom(me.aa07.paradise.taskdaemon.database.gamedb.Tables.DONATORS)
            .where(me.aa07.paradise.taskdaemon.database.gamedb.Tables.DONATORS.ACTIVE.ne((byte) 0x01)) // tinyint is a byte
            .execute();

        int insert_count = 0;
        int update_count = 0;
        // Now do our record stuff
        for (PatreonSupportersRecord psr : valid_records) {
            DonatorsRecord dr = gameJooq.selectFrom(me.aa07.paradise.taskdaemon.database.gamedb.Tables.DONATORS)
                .where(me.aa07.paradise.taskdaemon.database.gamedb.Tables.DONATORS.CKEY.eq(psr.getCkey()))
                .fetchOne();

            if (dr == null) {
                // Empty record - make a new one
                DonatorsRecord dr2 = gameJooq.newRecord(me.aa07.paradise.taskdaemon.database.gamedb.Tables.DONATORS);
                dr2.setPatreonName(psr.getMemberId().toString());
                dr2.setTier(patreonTier2ingameTier(psr.getDonationTier()));
                dr2.setCkey(psr.getCkey());
                dr2.setStartDate(null);
                dr2.setEndDate(null);
                dr2.setActive((byte) 0x01);

                dr2.store();

                insert_count++;
            } else {
                // Existing record - update
                dr.setTier(patreonTier2ingameTier(psr.getDonationTier()));
                dr.setCkey(psr.getCkey());
                dr.setStartDate(null);
                dr.setEndDate(null);
                dr.setActive((byte) 0x01);

                dr.store();

                update_count++;
            }
        }

        logger.info(String.format("[PatreonSync] Inserted %s records, updated %s records in the game DB.", insert_count, update_count));
    }
}
