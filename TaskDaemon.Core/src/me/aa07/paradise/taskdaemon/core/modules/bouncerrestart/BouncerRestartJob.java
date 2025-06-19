package me.aa07.paradise.taskdaemon.core.modules.bouncerrestart;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import me.aa07.paradise.taskdaemon.core.config.TgsConfig;
import me.aa07.paradise.taskdaemon.core.models.tgs.TokenResponseModel;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution // NO
public class BouncerRestartJob implements Job {

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
            System.out.println("[BouncerRestart] LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        Logger logger = logger_holder.get();

        // Now get our TGS config
        Object raw_tgs_cfg = datamap.get("TGS_CFG");
        Optional<TgsConfig> tgs_cfg_holder = Optional.empty();

        if (raw_tgs_cfg instanceof TgsConfig tgsCfg) {
            tgs_cfg_holder = Optional.of(tgsCfg);
        }

        if (!tgs_cfg_holder.isPresent()) {
            logger.error("[BouncerRestart] TGS_CFG WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        logger.info("[BouncerRestart] Generating TGS creds");

        TgsConfig tgs_cfg = tgs_cfg_holder.get();

        // Now do the actual TGS requesting stuff
        String creds = String.format("%s:%s", tgs_cfg.username, tgs_cfg.password);
        byte[] encoded_auth = Base64.getEncoder().encode(creds.getBytes(StandardCharsets.UTF_8));
        String auth_header = String.format("Basic %s", new String(encoded_auth));

        // This needs to be in try/catch
        try {
            // Get our auth token first
            HttpRequest httpreq = HttpRequest.newBuilder().uri(new URI(String.format("%s/api", tgs_cfg.tgsHost)))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .setHeader("Accept", "application/json")
                    .setHeader("Content-Type", "application/json")
                    .setHeader("Api", "Tgstation.Server.Api/10.0.0")
                    .setHeader("Authorization", auth_header).build();

            HttpClient client = HttpClient.newHttpClient();
            logger.info("[BouncerRestart] Sending auth token request");
            HttpResponse<String> response = client.send(httpreq, BodyHandlers.ofString());

            Gson gson = new Gson();
            TokenResponseModel tokres = gson.fromJson(response.body(), TokenResponseModel.class);

            // And send our restart request
            HttpRequest httreq2 = HttpRequest.newBuilder()
                    .uri(new URI(String.format("%s/api/DreamDaemon", tgs_cfg.tgsHost)))
                    .method("PATCH", HttpRequest.BodyPublishers.noBody())
                    .setHeader("Accept", "application/json")
                    .setHeader("Content-Type", "application/json")
                    .setHeader("Instance", "2")
                    .setHeader("Api", "Tgstation.Server.Api/10.0.0")
                    .setHeader("Authorization", String.format("Bearer %s", tokres.bearer)).build();

            HttpClient client2 = HttpClient.newHttpClient();
            logger.info("[BouncerRestart] Sending restart request");
            client2.send(httreq2, BodyHandlers.ofString());
        } catch (Exception e) {
            logger.error("Error in BouncerRestartJob!");
            logger.error(e);
        }

        logger.info("[BouncerRestart] Done");
    }
}
