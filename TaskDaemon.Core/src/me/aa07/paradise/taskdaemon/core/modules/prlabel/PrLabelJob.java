package me.aa07.paradise.taskdaemon.core.modules.prlabel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import me.aa07.paradise.taskdaemon.core.config.GithubConfig;
import me.aa07.paradise.taskdaemon.core.config.TgsConfig;
import me.aa07.paradise.taskdaemon.core.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.core.models.prlabel.PullRequest;
import me.aa07.paradise.taskdaemon.database.pullrequests.Tables;
import me.aa07.paradise.taskdaemon.database.pullrequests.enums.PrsPrStatus;
import me.aa07.paradise.taskdaemon.database.pullrequests.enums.VotesNewVoteType;
import me.aa07.paradise.taskdaemon.database.pullrequests.enums.VotesNewVotingGroup;
import me.aa07.paradise.taskdaemon.database.pullrequests.tables.records.PrsRecord;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHLabel;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution // NO
public class PrLabelJob implements Job {

    @Override
    public void execute(JobExecutionContext event) throws JobExecutionException {
        // INITIAL SETUP
        JobDataMap datamap = event.getMergedJobDataMap();

        // Get our logger - important
        Object raw_logger = datamap.get("LOGGER");
        Optional<Logger> logger_holder = Optional.empty();

        if (raw_logger instanceof Logger l2) {
            logger_holder = Optional.of(l2);
        }

        if (!logger_holder.isPresent()) {
            System.out.println("[PrLabelJob] LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
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
            logger.error("[PrLabelJob] DBCORE WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        DbCore dbcore = dbcore_holder.get();

        // Now get our config
        Object raw_github_config = datamap.get("GITHUBCFG");
        Optional<GithubConfig> github_cfg_holder = Optional.empty();

        if (raw_github_config instanceof GithubConfig githubCfg2) {
            github_cfg_holder = Optional.of(githubCfg2);
        }

        if (!github_cfg_holder.isPresent()) {
            logger.error("[PrLabelJob] GITHUBCFG WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        GithubConfig github_config = github_cfg_holder.get();

        // Now get our other config
        Object raw_tgs_config = datamap.get("TGSCFG");
        Optional<TgsConfig> tgs_cfg_holder = Optional.empty();

        if (raw_tgs_config instanceof TgsConfig tgsCfg2) {
            tgs_cfg_holder = Optional.of(tgsCfg2);
        }

        if (!tgs_cfg_holder.isPresent()) {
            logger.error("[PrLabelJob] TGSCFG WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        TgsConfig tgs_cfg = tgs_cfg_holder.get();

        // Initial setup is done - lets now load the important bits

        // Get this now - we need it
        DSLContext ctx = dbcore.jooq(DatabaseType.PullRequests);

        // Dictionary that holds all this in a nice lookup
        HashMap<Integer, PullRequest> prs = new HashMap<Integer, PullRequest>();

        // And init these
        // TM manager
        TestmergeManager tmm = new TestmergeManager(tgs_cfg, logger);
        tmm.setup();

        // Files changed manager
        FilesChangedManager fcm = new FilesChangedManager(logger);

        // Vote status manager
        StatusManager sm = new StatusManager(logger, github_config);
        // We set this up later to contain the exception

        // Put everything in a list to aid iteration
        ArrayList<IPullRequestProcessor> processors = new ArrayList<IPullRequestProcessor>();
        processors.add(tmm);
        processors.add(fcm);
        processors.add(sm);

        logger.info(String.format("[PrLabelJob] Loaded %s active TMs from TGS - now loading PRs from GitHub",
                tmm.loadedActiveTms()));

        // Evertything in the GH client throws IOException so we need to baby proof that
        try {
            // Set up the review team
            sm.setup();

            // Now set up the rest
            GitHub gh = GitHub.connectUsingOAuth(github_config.githubToken);
            GHRepository repo = gh.getRepositoryById(github_config.repoId);

            // Get our open PRs
            List<GHPullRequest> open_prs = repo.queryPullRequests().state(GHIssueState.OPEN).list().toList();

            // And parse them into that array
            for (GHPullRequest pr : open_prs) {
                PullRequest internal_pr = new PullRequest();
                internal_pr.prObject = pr;
                internal_pr.pullNumber = pr.getNumber();

                for (GHLabel label : pr.getLabels()) {
                    internal_pr.labels.add(label.getName());
                }

                prs.put(internal_pr.pullNumber, internal_pr);
            }

            logger.info(String.format("[PrLabelJob] Loaded %s PRs from GitHub - now loading from DB", prs.size()));

            // Now get the data from the DB, just the PR metadata
            Result<PrsRecord> db_prs = ctx.selectFrom(Tables.PRS).where(Tables.PRS.PR_STATUS.eq(PrsPrStatus.OPEN))
                    .fetch();

            for (PrsRecord db_pr : db_prs) {
                int pr_num = db_pr.getPrNumber().intValue();
                if (prs.containsKey(pr_num)) {
                    // Set values up
                    PullRequest pr = prs.get(pr_num);
                    pr.prTypes = db_pr.getPrTypes();
                } else {
                    logger.warn(String.format("[PrLabelJob] PR #%s in DB but not open!", pr_num));
                }
            }

            // Now the testmerge requests
            logger.info(String.format("[PrLabelJob] Processed %s DB PRs - now loading TMRs", prs.size()));

            Result<Record2<Integer, Integer>> db_tmrs = ctx.select(Tables.TM_REQUESTS.PR_NUMBER, DSL.count())
                    .from(Tables.TM_REQUESTS)
                    .leftJoin(Tables.PRS).on(Tables.PRS.PR_NUMBER.eq(Tables.TM_REQUESTS.PR_NUMBER))
                    .where(Tables.PRS.PR_STATUS.eq(PrsPrStatus.OPEN))
                    .groupBy(Tables.TM_REQUESTS.PR_NUMBER)
                    .fetch();

            for (Record2<Integer, Integer> db_tmr_rec : db_tmrs) {
                int pr_num = db_tmr_rec.value1().intValue();
                int tmr_count = db_tmr_rec.value2().intValue();

                if (prs.containsKey(pr_num)) {
                    // Set values up
                    PullRequest pr = prs.get(pr_num);
                    pr.testmergeRequests = tmr_count;
                }
            }

            // Now the PR votes
            logger.info(String.format("[PrLabelJob] Processed %s DB TMRs - now loading votes", db_tmrs.size()));

            Result<Record4<Integer, VotesNewVotingGroup, VotesNewVoteType, Integer>> db_votes = ctx.select(
                    Tables.VOTES_NEW.PR_NUMBER,
                    Tables.VOTES_NEW.VOTING_GROUP,
                    Tables.VOTES_NEW.VOTE_TYPE,
                    DSL.count()).from(Tables.VOTES_NEW)
                    .leftJoin(Tables.PRS).on(Tables.PRS.PR_NUMBER.eq(Tables.VOTES_NEW.PR_NUMBER))
                    .where(Tables.PRS.PR_STATUS.eq(PrsPrStatus.OPEN))
                    .groupBy(Tables.VOTES_NEW.PR_NUMBER, Tables.VOTES_NEW.VOTING_GROUP, Tables.VOTES_NEW.VOTE_TYPE)
                    .fetch();

            for (Record4<Integer, VotesNewVotingGroup, VotesNewVoteType, Integer> db_vote_rec : db_votes) {
                int pr_num = db_vote_rec.value1().intValue();
                VotesNewVotingGroup vg = db_vote_rec.value2();
                VotesNewVoteType vt = db_vote_rec.value3();
                int vote_count = db_vote_rec.value4().intValue();

                if (prs.containsKey(pr_num)) {
                    // Set values up
                    PullRequest pr = prs.get(pr_num);

                    if (!pr.votes.containsKey(vg)) {
                        pr.votes.put(vg, new HashMap<VotesNewVoteType, Integer>());
                    }

                    // No check needed - this is fine to add or replace
                    HashMap<VotesNewVoteType, Integer> pr_votes_map = pr.votes.get(vg);
                    pr_votes_map.put(vt, vote_count);
                }
            }

            // Now we have the votes we can actually parse the PRs
            logger.info(
                    String.format("[PrLabelJob] Processed %s DB votes sets - now doing the main thing",
                            db_votes.size()));

            for (int pr_number : prs.keySet()) {
                logger.info(String.format("[PrLabelJob] Processing #%s", pr_number));
                PullRequest pr_object = prs.get(pr_number);

                // Handle on each processor
                for (IPullRequestProcessor processor : processors) {
                    processor.processPr(pr_object);
                }
            }

            logger.info(String.format("[PrLabelJob] Done", db_votes.size()));

        } catch (IOException e) {
            logger.error("[PrLabelJob] Failed due to an IOException");
            logger.error(e);
            return;
        }
    }

}
