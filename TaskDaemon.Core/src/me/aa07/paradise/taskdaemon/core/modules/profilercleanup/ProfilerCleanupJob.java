package me.aa07.paradise.taskdaemon.core.modules.profilercleanup;

import java.util.Optional;
import me.aa07.paradise.taskdaemon.core.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.database.profiler.Tables;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution // NO
public class ProfilerCleanupJob implements Job {

    @Override
    public void execute(JobExecutionContext event) throws JobExecutionException {
        JobDataMap datamap = event.getMergedJobDataMap();

        // Get our logger - important
        Object raw_logger = datamap.get("LOGGER");
        Optional<Logger> logger_holder = Optional.empty();

        if (raw_logger instanceof Logger l2)  {
            logger_holder = Optional.of(l2);
        }

        if (!logger_holder.isPresent()) {
            System.out.println("LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        Logger logger = logger_holder.get();

        // Now get our DB
        Object raw_db = datamap.get("DBCORE");
        Optional<DbCore> dbcore_holder = Optional.empty();

        if (raw_db instanceof DbCore db2) {
            dbcore_holder = Optional.of(db2);
        }

        if (!dbcore_holder.isPresent()) {
            logger.error("DBCORE WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        DbCore dbcore = dbcore_holder.get();

        logger.info("Cleaning out profiler DB - proc samples");
        dbcore.jooq(DatabaseType.ProfilerDb).delete(Tables.SAMPLES)
            .where(Tables.SAMPLES.SAMPLETIME.lt(dbcore.now().minusDays(7))).execute();
        logger.info("Cleaning out profiler DB - sendmaps samples");
        dbcore.jooq(DatabaseType.ProfilerDb).delete(Tables.SENDMAPS_SAMPLES)
            .where(Tables.SENDMAPS_SAMPLES.SAMPLETIME.lt(dbcore.now().minusDays(7))).execute();
        logger.info("Cleaned");
    }

}
