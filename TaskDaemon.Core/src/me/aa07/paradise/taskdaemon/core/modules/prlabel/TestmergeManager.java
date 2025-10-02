package me.aa07.paradise.taskdaemon.core.modules.prlabel;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import me.aa07.paradise.taskdaemon.core.config.TgsConfig;
import me.aa07.paradise.taskdaemon.core.models.prlabel.PullRequest;
import me.aa07.paradise.taskdaemon.core.models.tgs.DreamDaemonResponse;
import me.aa07.paradise.taskdaemon.core.models.tgs.TestMerge;
import me.aa07.paradise.taskdaemon.core.models.tgs.TokenResponseModel;
import org.apache.logging.log4j.Logger;

public class TestmergeManager implements IPullRequestProcessor {
    private TgsConfig tgsConfig;
    private Logger logger;
    private boolean tgsPullSuccessful = false;
    private ArrayList<Integer> testmeredPrs = new ArrayList<Integer>();
    // Dont change these
    private static final String TMR_LABEL_NAME = "Testmerge Requested";
    private static final String TMA_LABEL_NAME = "Testmerge Active";

    public TestmergeManager(TgsConfig tgsConfig, Logger logger) {
        this.tgsConfig = tgsConfig;
        this.logger = logger;
    }

    @Override
    public void processPr(PullRequest pr) throws IOException {
        processTmRequest(pr);
        if (tgsPullSuccessful) {
            processTmActive(pr);
        }
    }

    public void setup() {
        String creds = String.format("%s:%s", tgsConfig.username, tgsConfig.password);
        byte[] encoded_auth = Base64.getEncoder().encode(creds.getBytes(StandardCharsets.UTF_8));
        String auth_header = String.format("Basic %s", new String(encoded_auth));
        try {
            // Get our auth token first
            HttpRequest httpreq = HttpRequest.newBuilder().uri(new URI(String.format("%s/api", tgsConfig.tgsHost)))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .setHeader("Accept", "application/json")
                    .setHeader("Content-Type", "application/json")
                    .setHeader("Api", "Tgstation.Server.Api/10.0.0")
                    .setHeader("Authorization", auth_header).build();

            HttpClient client = HttpClient.newHttpClient();
            logger.info("[PrLabelJob] [TestmergeManager] Sending auth token request");
            HttpResponse<String> response = client.send(httpreq, BodyHandlers.ofString());

            Gson gson = new Gson();
            TokenResponseModel tokres = gson.fromJson(response.body(), TokenResponseModel.class);

            HttpRequest httreq2 = HttpRequest.newBuilder()
                    .uri(new URI(String.format("%s/api/DreamDaemon", tgsConfig.tgsHost))).GET()
                    .setHeader("Accept", "application/json")
                    .setHeader("Content-Type", "application/json")
                    .setHeader("Instance", "1")
                    .setHeader("Api", "Tgstation.Server.Api/10.0.0")
                    .setHeader("Authorization", String.format("Bearer %s", tokres.bearer)).build();

            HttpClient client2 = HttpClient.newHttpClient();
            HttpResponse<String> response2 = client2.send(httreq2, BodyHandlers.ofString());

            DreamDaemonResponse ddr = gson.fromJson(response2.body(), DreamDaemonResponse.class);

            if (ddr.activeCompileJob.revisionInformation.activeTestMerges != null) {
                for (TestMerge tm : ddr.activeCompileJob.revisionInformation.activeTestMerges) {
                    testmeredPrs.add(tm.number);
                }
            }

            tgsPullSuccessful = true;
            logger.info(String.format("[PrLabelJob] [TestmergeManager] Pulled %s testmerges from TGS.",
                    testmeredPrs.size()));

        } catch (Exception e) {
            logger.error("Error in PrLabelJob/TestmergeManager!");
            logger.error(e);
        }
    }

    public int loadedActiveTms() {
        return testmeredPrs.size();
    }

    // Private methods
    private void processTmRequest(PullRequest pr) throws IOException {
        if (pr.testmergeRequests > 0) {
            if (pr.safeAddLabel(TMR_LABEL_NAME)) {
                logger.info(String.format("[PrLabelJob] [TestmergeManager] Adding TMR to #%s", pr.pullNumber));
            }
        } else {
            if (pr.safeDelLabel(TMR_LABEL_NAME)) {
                logger.info(String.format("[PrLabelJob] [TestmergeManager] Removing TMR from #%s", pr.pullNumber));
            }
        }
    }

    private void processTmActive(PullRequest pr) throws IOException {
        if (testmeredPrs.contains(pr.pullNumber)) {
            if (pr.safeAddLabel(TMA_LABEL_NAME)) {
                logger.info(String.format("[PrLabelJob] [TestmergeManager] Adding TMA to #%s", pr.pullNumber));
            }
        } else {
            if (pr.safeDelLabel(TMA_LABEL_NAME)) {
                logger.info(String.format("[PrLabelJob] [TestmergeManager] Removing TMA from #%s", pr.pullNumber));
            }
        }
    }
}
