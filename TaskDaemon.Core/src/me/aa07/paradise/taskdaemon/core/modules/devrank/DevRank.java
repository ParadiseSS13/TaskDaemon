package me.aa07.paradise.taskdaemon.core.modules.devrank;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.core.database.DatabaseType;
import me.aa07.paradise.taskdaemon.database.gamedb.Tables;
import me.aa07.paradise.taskdaemon.database.forumdb.Tables as ForumTables; // TODO: adjust as needed
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static java.lang.System.*;

/**
 * Scheduled job that syncs dev team permissions between forum and game databases
 */
public class DevRank implements Job {
    private static final int DEV_TEAM_GROUP = 39;
    private static final int DEV_TEAM_BITFLAG = 262144;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();

        // Get logger
        Object rawLogger = dataMap.get("LOGGER");
        Optional<Logger> loggerHolder = Optional.empty();

        if (rawLogger instanceof Logger l2) {
            loggerHolder = Optional.of(l2);
        }

        if (loggerHolder.isEmpty()) {
            System.out.println("[DevRank] LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }
        Logger logger = loggerHolder.get();

        // Get database
        Object rawDb = dataMap.get("DBCORE");
        Optional<DbCore> dbCoreHolder = Optional.empty();

        if (rawDb instanceof DbCore db2) {
            dbCoreHolder = Optional.of(db2);
        }

        if (dbCoreHolder.isEmpty()) {
            logger.error("[DevRank] DBCORE WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }
        DbCore dbcore = dbCoreHolder.get();
    }
}
