package me.aa07.paradise.taskdaemon.core.modules.profileringest;

import org.apache.logging.log4j.Logger;
import redis.clients.jedis.JedisPubSub;

public class ProfilerPubSubHandler extends JedisPubSub {
    private Logger logger;
    private ProfilerWorker worker;

    public ProfilerPubSubHandler(ProfilerWorker worker, Logger logger) {
        this.worker = worker;
        this.logger = logger;
    }

    @Override
    public void onMessage(String channel, String message) {
        if (!channel.equals("profilerdaemon.input")) {
            return; // Somehow
        }

        worker.safeAdd(message);
        logger.info("Queued a new profiler dump");
    }
}
