package me.aa07.paradise.taskdaemon.core.modules.kibanaaccess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import me.aa07.paradise.taskdaemon.core.services.authentik.AuthentikUtil;
import me.aa07.paradise.taskdaemon.core.services.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.services.database.DbCore;
import me.aa07.paradise.taskdaemon.core.util.UtilConst;
import me.aa07.paradise.taskdaemon.core.util.UtilStr;
import me.aa07.paradise.taskdaemon.database.authentik.tables.records.AuthentikCoreUserGroupsRecord;
import me.aa07.paradise.taskdaemon.database.authentik.tables.records.AuthentikCoreUsersourceconnectionRecord;
import me.aa07.paradise.taskdaemon.database.gamedb.tables.records.AdminRecord;
import org.apache.logging.log4j.core.Logger;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class KibanaAccessJob implements Job {

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
            System.out.println("[KibanaAccess] LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        Logger logger = logger_holder.get();
        logger.info("[KibanaAccess] Starting up");

        // Now get our DB
        Object raw_db = datamap.get("DBCORE");
        Optional<DbCore> dbcore_holder = Optional.empty();

        if (raw_db instanceof DbCore db2) {
            dbcore_holder = Optional.of(db2);
        }

        if (!dbcore_holder.isPresent()) {
            logger.error("[KibanaAccess] DBCORE WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        DbCore dbcore = dbcore_holder.get();

        // And the authentik util
        Object raw_au = datamap.get("AU");
        Optional<AuthentikUtil> au_holder = Optional.empty();

        if (raw_au instanceof AuthentikUtil au2) {
            au_holder = Optional.of(au2);
        }

        if (!au_holder.isPresent()) {
            logger.error("[KibanaAccess] AU WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        AuthentikUtil au = au_holder.get();

        HashSet<String> active_staff = new HashSet<String>();

        // Step 1 - Get list of ingame people
        HashSet<Integer> valid_admin_ranks = new HashSet<Integer>();
        valid_admin_ranks.add(UtilConst.INGAME_RANK_CM);
        valid_admin_ranks.add(UtilConst.INGAME_RANK_DEV);
        valid_admin_ranks.add(UtilConst.INGAME_RANK_GA);
        valid_admin_ranks.add(UtilConst.INGAME_RANK_HOS);
        valid_admin_ranks.add(UtilConst.INGAME_RANK_HC);
        valid_admin_ranks.add(UtilConst.INGAME_RANK_SDEV);
        valid_admin_ranks.add(UtilConst.INGAME_RANK_SHOST);
        valid_admin_ranks.add(UtilConst.INGAME_RANK_TA);

        DSLContext game_jooq = dbcore.jooq(DatabaseType.GameDb);
        DSLContext authentik_jooq = dbcore.jooq(DatabaseType.Authentik);

        Result<AdminRecord> ingame_staff_raw = game_jooq
            .selectFrom(me.aa07.paradise.taskdaemon.database.gamedb.Tables.ADMIN)
            .where(me.aa07.paradise.taskdaemon.database.gamedb.Tables.ADMIN.PERMISSIONS_RANK.in(valid_admin_ranks))
            .fetch();

        for (AdminRecord ar : ingame_staff_raw) {
            active_staff.add(ar.getCkey());
        }

        logger.info(String.format("[KibanaAccess] Loaded %s ingame staff with Kibana access", active_staff.size()));

        // Step 2 - Get all authentik CKEY links and create maps
        HashMap<String, Integer> ckey2authentik = new HashMap<String, Integer>();
        HashMap<Integer, String> authentik2ckey = new HashMap<Integer, String>();

        Result<AuthentikCoreUsersourceconnectionRecord> authentik_records = authentik_jooq
            .selectFrom(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION)
            .where(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USERSOURCECONNECTION.SOURCE_ID.eq(UtilConst.BYOND_OAUTH_SERVICE_ID))
            .fetch();

        for (AuthentikCoreUsersourceconnectionRecord acuscr : authentik_records) {
            String clean_ckey = UtilStr.cleanCkey(acuscr.getIdentifier());
            int authentik_id = acuscr.getUserId();

            ckey2authentik.put(clean_ckey, authentik_id);
            authentik2ckey.put(authentik_id, clean_ckey);
        }

        logger.info(String.format("[KibanaAccess] Found %s accounts with linked byond accounts", ckey2authentik.size()));

        // Now get existing Kibana access
        Result<AuthentikCoreUserGroupsRecord> existing_access_groups = authentik_jooq
            .selectFrom(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USER_GROUPS)
            .where(me.aa07.paradise.taskdaemon.database.authentik.Tables.AUTHENTIK_CORE_USER_GROUPS.GROUP_ID.eq(UtilConst.AUTHENTIK_GROUP_KIBANAACCESS))
            .fetch();

        HashSet<Integer> existing_kibana_access = new HashSet<Integer>();

        for (AuthentikCoreUserGroupsRecord acugr : existing_access_groups) {
            existing_kibana_access.add(acugr.getUserId());
        }

        logger.info(String.format("[KibanaAccess] Found %s accounts with Kibana access", existing_kibana_access.size()));

        // Now add the group to those missing it
        for (String ckey : active_staff) {
            if (!ckey2authentik.containsKey(ckey)) {
                logger.warn(String.format("[KibanaAccess] %s has no linked account - cannot add role", ckey));
                continue;
            }

            int authentik_id = ckey2authentik.get(ckey);

            if (existing_kibana_access.contains(authentik_id)) {
                logger.info(String.format("[KibanaAccess] %s already has Kibana access - skipping", ckey));
                continue;
            }

            // Add the group
            logger.info(String.format("[KibanaAccess] Adding %s to Kibana access", ckey));
            au.addUserToGroup(authentik_id, UtilConst.AUTHENTIK_GROUP_KIBANAACCESS);
        }

        // Now remove those who shouldnt be in there
        for (int authentik_id : existing_kibana_access) {
            if (!authentik2ckey.containsKey(authentik_id)) {
                logger.warn(String.format("[KibanaAccess] Authentik ID %s has no linked account - removing role", authentik_id));
                au.removeUserFromGroup(authentik_id, UtilConst.AUTHENTIK_GROUP_KIBANAACCESS);
                continue;
            }

            String ckey = authentik2ckey.get(authentik_id);

            if (!active_staff.contains(ckey)) {
                logger.info(String.format("[KibanaAccess] %s is no longer staff - removing role", ckey));
                au.removeUserFromGroup(authentik_id, UtilConst.AUTHENTIK_GROUP_KIBANAACCESS);
            }
        }

        logger.info("[KibanaAccess] Process complete");
    }
}
