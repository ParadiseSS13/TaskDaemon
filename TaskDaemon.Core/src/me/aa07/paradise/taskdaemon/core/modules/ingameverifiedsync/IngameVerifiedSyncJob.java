package me.aa07.paradise.taskdaemon.core.modules.ingameverifiedsync;

import java.util.ArrayList;
import java.util.Optional;
import me.aa07.paradise.taskdaemon.core.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.core.util.UtilConst;
import me.aa07.paradise.taskdaemon.core.util.UtilStr;
import me.aa07.paradise.taskdaemon.database.authentik.tables.records.AuthentikCoreUsersourceconnectionRecord;
import me.aa07.paradise.taskdaemon.database.automation.Tables;
import me.aa07.paradise.taskdaemon.database.automation.tables.records.IngameVerifiedDirectoryRecord;
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

        DSLContext authentik_jooq = dbcore.jooq(DatabaseType.Authentik);
        DSLContext automation_jooq = dbcore.jooq(DatabaseType.Automation);

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

            // Step 2 - Strip their rank on the discord

            // Step 3 - Clear their FUID ingame

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

        logger.info("[IngameVerifiedSync] Process complete");
    }
}
