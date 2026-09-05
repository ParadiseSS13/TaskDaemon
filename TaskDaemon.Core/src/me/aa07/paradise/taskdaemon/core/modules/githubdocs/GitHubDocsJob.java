package me.aa07.paradise.taskdaemon.core.modules.githubdocs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;
import java.util.Optional;
import me.aa07.paradise.taskdaemon.core.config.sections.GithubDocsConfig;
import me.aa07.paradise.taskdaemon.core.models.github.GetConentsResponseModel;
import me.aa07.paradise.taskdaemon.core.services.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.services.database.DbCore;
import me.aa07.paradise.taskdaemon.core.util.UtilConst;
import me.aa07.paradise.taskdaemon.database.automation.Tables;
import me.aa07.paradise.taskdaemon.database.automation.tables.records.DocumentsRecord;
import org.apache.logging.log4j.core.Logger;
import org.jooq.DSLContext;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


@DisallowConcurrentExecution // NO
public class GitHubDocsJob implements Job {
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
            System.out.println("[GitHubDocs] LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
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
            logger.error("[GitHubDocs] DBCORE WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        DbCore dbcore = dbcore_holder.get();

        // Now get our config
        Object raw_ghdc = datamap.get("GHDC");
        Optional<GithubDocsConfig> ghdc_holder = Optional.empty();

        if (raw_ghdc instanceof GithubDocsConfig ghdc2) {
            ghdc_holder = Optional.of(ghdc2);
        }

        if (!ghdc_holder.isPresent()) {
            logger.error("[GitHubDocs] GHDC WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        logger.info("[GitHubDocs] Starting up");

        GithubDocsConfig ghdc = ghdc_holder.get();
        DSLContext ctx = dbcore.jooq(DatabaseType.Automation);

        // Now do the API stuff
        HttpClient client = HttpClient.newHttpClient();
        for (GithubDocsConfig.DocHolder dh : ghdc.docs) {
            logger.info(String.format("[GitHubDocs] Loading %s/%s: (%s)", dh.repoSlug, dh.docPath, dh.docKey));
            String api_path = String.format(
                "https://api.github.com/repos/%s/contents/%s?ref=master",
                dh.repoSlug,
                dh.docPath
            );

            try {
                HttpRequest httpreq = HttpRequest.newBuilder()
                    .uri(new URI(api_path))
                    .GET()
                    .header("Accept", "application/json")
                    .header("Authorization", String.format("Bearer %s", dh.apiKey))
                    .build();

                // Send the request off
                HttpResponse<String> response = client.send(httpreq, BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    logger.warn(String.format("[GitHubDocs] Got a non-200 status code while processing (%s) - skipping", response.statusCode()));
                    continue;
                }

                GetConentsResponseModel res_model = UtilConst.GSON.fromJson(response.body(), GetConentsResponseModel.class);
                if (res_model == null) {
                    logger.warn(String.format("[GitHubDocs] Got a null model when processing - skipping", response.statusCode()));
                    continue;
                }

                // Now get our doc itself - you have to use the MIME decoder otherwise it freaks out
                byte[] decoded_bytes = Base64.getMimeDecoder().decode(res_model.content);
                String decoded_doc = new String(decoded_bytes);

                // Now do the DB
                DocumentsRecord dr = ctx.selectFrom(Tables.DOCUMENTS)
                    .where(Tables.DOCUMENTS.DOC_NAME.eq(dh.docKey))
                    .fetchOne();

                if (dr == null) {
                    // Null record - make a new one
                    DocumentsRecord ndr = ctx.newRecord(Tables.DOCUMENTS);
                    ndr.setDocName(dh.docKey);
                    ndr.setDocContents(decoded_doc);
                    ndr.store();
                } else {
                    dr.setDocContents(decoded_doc);
                    dr.store();
                }


            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

        }

        logger.info("[GitHubDocs] Processing Finished");
    }

}
