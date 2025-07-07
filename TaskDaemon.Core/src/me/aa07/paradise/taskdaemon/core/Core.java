package me.aa07.paradise.taskdaemon.core;

import com.moandjiezana.toml.Toml;
import java.io.File;
import java.util.Optional;
import me.aa07.paradise.taskdaemon.core.config.ConfigHolder;
import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.core.modules.aclcleanup.AclCleanupJob;
import me.aa07.paradise.taskdaemon.core.modules.bouncerrestart.BouncerRestartJob;
import me.aa07.paradise.taskdaemon.core.modules.devrank.DevRankJob;
import me.aa07.paradise.taskdaemon.core.modules.ip2asn.Ip2AsnJob;
import me.aa07.paradise.taskdaemon.core.modules.profilercleanup.ProfilerCleanupJob;
import me.aa07.paradise.taskdaemon.core.modules.profileringest.ProfilerWorker;
import me.aa07.paradise.taskdaemon.core.redis.RedisManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Core {
    // Threads
    private Thread profilerWorkerThread;
    private Thread redisthread;

    public void run() {
        Logger logger = LogManager.getLogger(Core.class);
        logger.info("Starting up");
        logger.info("Loading configuration...");

        Optional<ConfigHolder> config = Optional.empty();

        try {
            File configfile = new File("config.toml");
            config = Optional.of(new Toml().read(configfile).to(ConfigHolder.class));
        } catch (Exception exception) {
            logger.fatal("Failed to load config.toml!");
            exception.printStackTrace();
            return;
        }

        if (!config.isPresent()) {
            logger.fatal("Failed to load config.toml - but we didnt throw an exception! What the heck?!");
            return;
        }

        logger.info("Preparing threads...");
        setupThreads(config.get(), logger);
        logger.info("Launching threads...");
        launchAll();
        logger.info("Core startup complete");
    }

    private void setupThreads(ConfigHolder config, Logger logger) {
        // Initial setup
        DbCore database = new DbCore(config, logger);

        // Profiler stuff
        ProfilerWorker pw = new ProfilerWorker(database, logger);
        profilerWorkerThread = new Thread(pw::run, "profiler-worker");

        // Redis stuff
        RedisManager rm = new RedisManager(config.redis, logger, pw);
        redisthread = new Thread(rm::run, "redis");

        // Launch Quartz
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            setupJobs(scheduler, database, config, logger);
            scheduler.start();
        } catch (SchedulerException ex) {
            logger.error("Quartz had a hissy fit!", ex);
        }
    }

    private void setupJobs(Scheduler scheduler, DbCore dbCore, ConfigHolder config, Logger logger) throws SchedulerException {
        // See below for CRON format
        // https://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html

        // ACL cleanup
        JobDataMap jdm_aclcleanup = new JobDataMap();
        jdm_aclcleanup.put("LOGGER", logger);
        jdm_aclcleanup.put("DBCORE", dbCore);
        jdm_aclcleanup.put("PFSENSE_CFG", config.pfsense);
        JobDetail jd_aclcleanup = JobBuilder.newJob(AclCleanupJob.class)
            .withIdentity("aclcleanup", "aclcleanup")
            .usingJobData(jdm_aclcleanup)
            .build();
        CronTrigger ct_aclcleanup = TriggerBuilder.newTrigger()
            .withIdentity("aclcleanup", "aclcleanup")
            .withSchedule(CronScheduleBuilder.cronSchedule("0 */10 * * * ?"))
            .build();

        // Bouncer restart
        JobDataMap jdm_bouncerrestart = new JobDataMap();
        jdm_bouncerrestart.put("LOGGER", logger);
        jdm_bouncerrestart.put("TGS_CFG", config.tgs);
        JobDetail jd_bouncerrestart = JobBuilder.newJob(BouncerRestartJob.class)
            .withIdentity("bouncerrestart", "bouncerrestart")
            .usingJobData(jdm_bouncerrestart)
            .build();
        CronTrigger ct_bouncerrestart = TriggerBuilder.newTrigger()
            .withIdentity("bouncerrestart", "bouncerrestart")
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?")) // Every day - midnight
            .build();

        // DevRank update
        JobDataMap jdm_devrank = new JobDataMap();
        jdm_devrank.put("LOGGER", logger);
        jdm_devrank.put("DBCORE", dbCore);

        JobDetail jd_devrank = JobBuilder.newJob(DevRankJob.class)
            .withIdentity("devrank", "devrank")
            .usingJobData(jdm_devrank)
            .build();

        CronTrigger ct_devrank = TriggerBuilder.newTrigger()
            .withIdentity("devrank", "devrank")
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?")) // Every hour on the hour
            .build();


        // IP2ASN
        JobDataMap jdm_ip2asn = new JobDataMap();
        jdm_ip2asn.put("LOGGER", logger);
        jdm_ip2asn.put("DBCORE", dbCore);
        jdm_ip2asn.put("IP2ASNCFG", config.ip2asn);
        JobDetail jd_ip2asn = JobBuilder.newJob(Ip2AsnJob.class)
            .withIdentity("ip2asn", "ip2asn")
            .usingJobData(jdm_ip2asn)
            .build();
        CronTrigger ct_ip2asn = TriggerBuilder.newTrigger()
            .withIdentity("ip2asn", "ip2asn")
            .withSchedule(CronScheduleBuilder.cronSchedule("0 */10 * * * ?")) // Every 10 minutes
            .build();

        // Profiler cleanup
        JobDataMap jdm_profilercleanup = new JobDataMap();
        jdm_profilercleanup.put("LOGGER", logger);
        jdm_profilercleanup.put("DBCORE", dbCore);
        JobDetail jd_profilercleanup = JobBuilder.newJob(ProfilerCleanupJob.class)
            .withIdentity("profilercleanup", "profilercleanup")
            .usingJobData(jdm_profilercleanup)
            .build();
        CronTrigger ct_profilercleanup = TriggerBuilder.newTrigger()
            .withIdentity("profilercleanup", "profilercleanup")
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0 8 * * ?")) // Every day - 8AM
            .build();

        // Schedule all
        scheduler.scheduleJob(jd_aclcleanup, ct_aclcleanup);
        scheduler.scheduleJob(jd_bouncerrestart, ct_bouncerrestart);
        scheduler.scheduleJob(jd_devrank, ct_devrank);
        scheduler.scheduleJob(jd_ip2asn, ct_ip2asn);
        scheduler.scheduleJob(jd_profilercleanup, ct_profilercleanup);
    }

    private void launchAll() {
        profilerWorkerThread.start();
        redisthread.start();
    }
}
