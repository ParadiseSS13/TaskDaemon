package me.aa07.paradise.taskdaemon.core.redis;

import me.aa07.paradise.taskdaemon.core.config.RedisConfig;
import me.aa07.paradise.taskdaemon.core.modules.profileringest.ProfilerPubSubHandler;
import me.aa07.paradise.taskdaemon.core.modules.profileringest.ProfilerWorker;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.JedisPool;

public class RedisManager {
    private JedisPool pool;
    private RedisConfig config;
    private Logger logger;
    // Handlers
    private ProfilerPubSubHandler profilerHandler;

    public RedisManager(RedisConfig config, Logger logger, ProfilerWorker worker) {
        this.config = config;
        this.logger = logger;

        profilerHandler = new ProfilerPubSubHandler(worker, logger);
    }

    public void run() {
        logger.info("Starting...");
        pool = new JedisPool(config.host);
        logger.info("Connected. Subscribing profilerHandler...");
        pool.getResource().subscribe(profilerHandler, "profilerdaemon.input");
        logger.info("Done");
    }
}
