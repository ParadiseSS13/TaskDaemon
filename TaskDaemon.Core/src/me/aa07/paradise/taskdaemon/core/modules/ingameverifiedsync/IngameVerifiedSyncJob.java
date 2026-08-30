package me.aa07.paradise.taskdaemon.core.modules.ingameverifiedsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import me.aa07.paradise.taskdaemon.core.models.tasks.DiscordRoleTaskArgsModel;
import me.aa07.paradise.taskdaemon.core.services.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.services.database.DbCore;
import me.aa07.paradise.taskdaemon.core.services.invision.InvisionUtil;
import me.aa07.paradise.taskdaemon.core.util.UtilConst;
import me.aa07.paradise.taskdaemon.core.util.UtilStr;
import me.aa07.paradise.taskdaemon.database.authentik.tables.records.AuthentikCoreUsersourceconnectionRecord;
import me.aa07.paradise.taskdaemon.database.automation.Tables;
import me.aa07.paradise.taskdaemon.database.automation.enums.TaskQueueTaskConsumer;
import me.aa07.paradise.taskdaemon.database.automation.tables.records.IngameVerifiedDirectoryRecord;
import me.aa07.paradise.taskdaemon.database.automation.tables.records.TaskQueueRecord;
import me.aa07.paradise.taskdaemon.database.gamedb.tables.records.PlayerRecord;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution // NO
public class IngameVerifiedSyncJob implements Job {
    /*

    - pull raw data from authentik
    - keep track of new ones
    - get discord IDs and add into table
    - get forum IDs and add into table
    - do forum syncage based on if their IGV or IGB
    - remove entries from directory table that werent there before
      - schedule task in ALICE to strip discord role
      - remove forum group
    - slap authentik ID onto rows in the player table

    */

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
            System.out.println("[IngameVerifiedSync] LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        Logger logger = logger_holder.get();
        logger.info("[IngameVerifiedSync] Starting up");

        // Now get our DB
        Object raw_db = datamap.get("DBCORE");
        Optional<DbCore> dbcore_holder = Optional.empty();

        if (raw_db instanceof DbCore db2) {
            dbcore_holder = Optional.of(db2);
        }

        if (!dbcore_holder.isPresent()) {
            logger.error("[IngameVerifiedSync] DBCORE WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        DbCore dbcore = dbcore_holder.get();

        // Now get the invision util
        Object raw_iu = datamap.get("IU");
        Optional<InvisionUtil> iu_holder = Optional.empty();

        if (raw_iu instanceof InvisionUtil iu2) {
            iu_holder = Optional.of(iu2);
        }

        if (!iu_holder.isPresent()) {
            logger.error("[IngameVerifiedSync] IU WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        InvisionUtil iu = iu_holder.get();

        DSLContext authentik_jooq = dbcore.jooq(DatabaseType.Authentik);
        DSLContext automation_jooq = dbcore.jooq(DatabaseType.Automation);
        DSLContext game_jooq = dbcore.jooq(DatabaseType.GameDb);

        // For now - we just want ckeys and authentik IDs - no more
        Result<AuthentikCoreUsersourceconnectionRecord> authentik_records = authentik_jooq
                .selectFrom(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION)
                .where(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.SOURCE_ID.eq(UtilConst.BYOND_OAUTH_SERVICE_ID))
                .fetch();

        logger.info(String.format("[IngameVerifiedSync] Found %s accounts with linked byond accounts", authentik_records.size()));

        ArrayList<Integer> only_authentik_ids = new ArrayList<Integer>();

        for (AuthentikCoreUsersourceconnectionRecord acuscr : authentik_records) {
            only_authentik_ids.add(acuscr.getUserId());
        }

        // Records to purge
        Result<IngameVerifiedDirectoryRecord> records_to_purge = automation_jooq.selectFrom(Tables.INGAME_VERIFIED_DIRECTORY)
            .where(Tables.INGAME_VERIFIED_DIRECTORY.AUTHENTIK_ID.notIn(only_authentik_ids))
            .fetch();

        int removed = 0;
        for (IngameVerifiedDirectoryRecord ivdr : records_to_purge) {
            // Step 1 - Strip their rank on the forums
            if (ivdr.getForumId() != null) {
                int forum_id = ivdr.getForumId();
                Pair<Boolean, List<Integer>> gsg_response = iu.getUserSecondaryGroups(forum_id);

                if (gsg_response.getLeft()) {
                    List<Integer> forum_secondaries = gsg_response.getRight();
                    if (forum_secondaries.contains(Integer.valueOf(UtilConst.INVISION_INGAMEVERIFIED_GID))) {
                        // This cast looks silly - but without it, java treats it as a numerical index not an object
                        forum_secondaries.remove(Integer.valueOf(UtilConst.INVISION_INGAMEVERIFIED_GID));

                        boolean ssg_response = iu.updateUserSecondaryGroups(forum_id, forum_secondaries);

                        if (!ssg_response) {
                            logger.warn(String.format("[IngameVerifiedSync] Could not update secondaries for %s", forum_id));
                        }
                    }
                } else {
                    logger.warn(String.format("[IngameVerifiedSync] Could not get secondaries for %s", forum_id));
                }
            }

            // Step 2 - Strip their rank on the discord
            if (ivdr.getDiscordId() != null) {
                TaskQueueRecord tqr = automation_jooq.newRecord(Tables.TASK_QUEUE);
                tqr.setTaskId(UUID.randomUUID());
                tqr.setTaskConsumer(TaskQueueTaskConsumer.ALICE);
                tqr.setTaskType("REMOVE_IGV_ROLE");

                // No I will not apologise for these variable names
                DiscordRoleTaskArgsModel drtam = new DiscordRoleTaskArgsModel();
                drtam.discordId = ivdr.getDiscordId();
                String drtam_json = UtilConst.GSON.toJson(drtam);

                tqr.setTaskArguments(drtam_json);
                tqr.setDateInserted(dbcore.now());
                tqr.store();
            }

            // Step 3 - Clear their FUID ingame
            PlayerRecord ingame_record = game_jooq.selectFrom(me.aa07.paradise.taskdaemon.database.gamedb.Tables.PLAYER)
                .where(me.aa07.paradise.taskdaemon.database.gamedb.Tables.PLAYER.CKEY.eq(ivdr.getCkey()))
                .fetchOne();

            if (ingame_record != null) {
                ingame_record.setFuid(null);
                ingame_record.store();
            }

            // Step 4 - Clear from directory
            ivdr.delete();
            removed++;
        }
        logger.info(String.format("[IngameVerifiedSync] Removed %s records for members who unlinked byond", removed));

        // Now begin the mass inserts
        int raw_added = 0;
        int raw_updated = 0;
        for (AuthentikCoreUsersourceconnectionRecord acuscr : authentik_records) {
            int authentik_user_ud = acuscr.getUserId();
            // First - see if they already exist in the directory

            if (automation_jooq.fetchExists(automation_jooq.selectFrom(Tables.INGAME_VERIFIED_DIRECTORY)
                .where(Tables.INGAME_VERIFIED_DIRECTORY.AUTHENTIK_ID.eq(authentik_user_ud)))) {

                // Update if they do
                raw_updated++;

                IngameVerifiedDirectoryRecord ivdr = automation_jooq.selectFrom(Tables.INGAME_VERIFIED_DIRECTORY)
                    .where(Tables.INGAME_VERIFIED_DIRECTORY.AUTHENTIK_ID.eq(authentik_user_ud)).fetchOne();

                ivdr.setCkey(UtilStr.cleanCkey(acuscr.getIdentifier()));
                ivdr.store();
            } else {
                // Insert if they dont
                IngameVerifiedDirectoryRecord ivdr = automation_jooq.newRecord(Tables.INGAME_VERIFIED_DIRECTORY);
                ivdr.setAuthentikId(acuscr.getUserId());
                ivdr.setCkey(UtilStr.cleanCkey(acuscr.getIdentifier()));
                ivdr.store();
                raw_added++;
            }
        }

        logger.info(String.format("[IngameVerifiedSync] Added %s records and updated %s for members who are linked", raw_added, raw_updated));

        // Now slap forum IDs and discord onto them
        Result<IngameVerifiedDirectoryRecord> records_to_update = automation_jooq
                .selectFrom(Tables.INGAME_VERIFIED_DIRECTORY)
                .fetch();

        Result<AuthentikCoreUsersourceconnectionRecord> authentik_forums_records = authentik_jooq
                .selectFrom(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION)
                .where(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.SOURCE_ID.eq(UtilConst.INVISION_OAUTH_SERVICE_ID))
                .fetch();

        Result<AuthentikCoreUsersourceconnectionRecord> authentik_discord_records = authentik_jooq
                .selectFrom(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION)
                .where(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.SOURCE_ID.eq(UtilConst.DISCORD_OAUTH_SERVICE_ID))
                .fetch();

        Result<PlayerRecord> gamedb_records = game_jooq.selectFrom(me.aa07.paradise.taskdaemon.database.gamedb.Tables.PLAYER).fetch();

        HashMap<Integer, Integer> authentik2forum = new HashMap<Integer, Integer>();
        HashMap<Integer, Long> authentik2discord = new HashMap<Integer, Long>();
        HashMap<String, PlayerRecord> ckey2gamerecord = new HashMap<String, PlayerRecord>();

        for (AuthentikCoreUsersourceconnectionRecord acuscr : authentik_forums_records) {
            String forum_id = UtilStr.onlyNumbers(acuscr.getIdentifier()); // Some of these have UTF8-BOMs on them. I wish I was kidding.
            authentik2forum.put(acuscr.getUserId(), Integer.parseInt(forum_id));
        }

        for (AuthentikCoreUsersourceconnectionRecord acuscr : authentik_discord_records) {
            authentik2discord.put(acuscr.getUserId(), Long.parseLong(acuscr.getIdentifier()));
        }

        for (PlayerRecord pr : gamedb_records) {
            ckey2gamerecord.put(pr.getCkey(), pr);
        }

        int forum_updated = 0;
        int discord_updated = 0;
        int game_updated = 0;
        for (IngameVerifiedDirectoryRecord ivdr : records_to_update) {
            boolean update_made = false;
            int authentik_id = ivdr.getAuthentikId();
            int forum_id = 0;
            long discord_id = 0L;

            if (authentik2forum.containsKey(authentik_id)) {
                forum_id = authentik2forum.get(authentik_id);
            }

            if (authentik2discord.containsKey(authentik_id)) {
                discord_id = authentik2discord.get(authentik_id);
            }

            // Add forums
            if (forum_id > 0 && (ivdr.getForumId() == null || !ivdr.getForumId().equals(forum_id))) {
                // See if we have them as an ID
                ivdr.setForumId(forum_id);
                update_made = true;
                forum_updated++;

                // ADD INVISION SECONDARY GROUPS HERE
                Pair<Boolean, List<Integer>> gsg_response = iu.getUserSecondaryGroups(forum_id);

                if (gsg_response.getLeft()) {
                    List<Integer> forum_secondaries = gsg_response.getRight();
                    if (!forum_secondaries.contains(UtilConst.INVISION_INGAMEVERIFIED_GID)) {
                        forum_secondaries.add(UtilConst.INVISION_INGAMEVERIFIED_GID);

                        boolean ssg_response = iu.updateUserSecondaryGroups(forum_id, forum_secondaries);

                        if (!ssg_response) {
                            logger.warn(String.format("[IngameVerifiedSync] Could not update secondaries for %s", forum_id));
                        }
                    }
                } else {
                    logger.warn(String.format("[IngameVerifiedSync] Could not get secondaries for %s", forum_id));
                }
            }

            // Add discord
            if (discord_id > 0 && (ivdr.getDiscordId() == null || !ivdr.getDiscordId().equals(discord_id))) {
                ivdr.setDiscordId(discord_id);
                update_made = true;
                discord_updated++;

                // And make the ALICE task
                TaskQueueRecord tqr = automation_jooq.newRecord(Tables.TASK_QUEUE);
                tqr.setTaskId(UUID.randomUUID());
                tqr.setTaskConsumer(TaskQueueTaskConsumer.ALICE);
                tqr.setTaskType("ADD_IGV_ROLE");

                // No I will not apologise for these variable names
                DiscordRoleTaskArgsModel drtam = new DiscordRoleTaskArgsModel();
                drtam.discordId = discord_id;
                String drtam_json = UtilConst.GSON.toJson(drtam);

                tqr.setTaskArguments(drtam_json);
                tqr.setDateInserted(dbcore.now());
                tqr.store();
            }

            if (ckey2gamerecord.containsKey(ivdr.getCkey())) {
                PlayerRecord game_row = ckey2gamerecord.get(ivdr.getCkey());
                if (game_row.getFuid() == null || game_row.getFuid() != authentik_id) {
                    game_row.setFuid((long) authentik_id);
                    game_row.store();
                    game_updated++;
                }
            }

            if (update_made) {
                ivdr.store();
            }
        }

        logger.info(String.format("[IngameVerifiedSync] Update stats discord/forum/game - %s/%s/%s", discord_updated, forum_updated, game_updated));
        logger.info("[IngameVerifiedSync] Process complete");
    }
}
