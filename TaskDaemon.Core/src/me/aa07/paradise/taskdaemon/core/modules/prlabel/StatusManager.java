package me.aa07.paradise.taskdaemon.core.modules.prlabel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.aa07.paradise.taskdaemon.core.config.GithubConfig;
import me.aa07.paradise.taskdaemon.core.models.prlabel.PullRequest;
import me.aa07.paradise.taskdaemon.database.pullrequests.enums.VotesNewVoteType;
import me.aa07.paradise.taskdaemon.database.pullrequests.enums.VotesNewVotingGroup;
import org.apache.logging.log4j.Logger;
import org.kohsuke.github.GHPullRequestReview;
import org.kohsuke.github.GHPullRequestReviewState;
import org.kohsuke.github.GHTeam;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

public class StatusManager implements IPullRequestProcessor {
    private Logger logger;
    private GithubConfig githubConfig;
    private ArrayList<Long> reviewTeamMembers = new ArrayList<Long>();

    // Dont change these
    private static final String LABEL_AWAITING_TYPE = "-Status: Awaiting type assignment";
    private static final String LABEL_AWAITING_APPROVAL = "-Status: Awaiting approval";
    private static final String LABEL_AWAITING_REVIEW = "-Status: Awaiting review";
    private static final String LABEL_AWAITING_MERGE = "-Status: Awaiting merge";

    public StatusManager(Logger logger, GithubConfig githubConfig) {
        this.logger = logger;
        this.githubConfig = githubConfig;
    }

    public void setup() throws IOException {
        GitHub gh = GitHub.connectUsingOAuth(githubConfig.reviewTeamToken);
        GHTeam team = gh.getOrganization(githubConfig.orgName).getTeamBySlug(githubConfig.reviewTeamSlug);

        for (GHUser user : team.getMembers()) {
            reviewTeamMembers.add(user.getId());
        }

        logger.info(String.format("[PrLabelJob] [StatusManager] Loaded %s members in the review team",
                reviewTeamMembers.size()));
    }

    @Override
    public void processPr(PullRequest pr) throws IOException {
        // If its empty JSON it doesnt have a type yet
        if (pr.prTypes.equalsIgnoreCase("[]")) {
            if (pr.safeAddLabel(LABEL_AWAITING_TYPE)) {
                logger.info(
                        String.format("[PrLabelJob] [StatusManager] Labelling #%s as awaiting type", pr.pullNumber));
            }
            pr.safeDelLabel(LABEL_AWAITING_APPROVAL);
            pr.safeDelLabel(LABEL_AWAITING_REVIEW);
            pr.safeDelLabel(LABEL_AWAITING_MERGE);
            return;
        }

        // If we got here we can begin the rest
        Type inner_string_type = new TypeToken<ArrayList<String>>() {
        }.getType();
        List<String> pr_types = new Gson().fromJson(pr.prTypes, inner_string_type);

        boolean passes_approval = false;
        boolean passes_review = false;

        if (pr_types.contains("Sprites") || pr_types.contains("Design")) {
            int approval_count = 0;
            int objection_count = 0;

            ArrayList<VotesNewVotingGroup> valid_groups = new ArrayList<VotesNewVotingGroup>();
            valid_groups.add(VotesNewVotingGroup.DESIGN);
            valid_groups.add(VotesNewVotingGroup.VETO);
            valid_groups.add(VotesNewVotingGroup.LEGACY);
            valid_groups.add(VotesNewVotingGroup.SPRITE);

            for (VotesNewVotingGroup group : valid_groups) {
                if (pr.votes.containsKey(group)) {
                    HashMap<VotesNewVoteType, Integer> vote_map = pr.votes.get(group);

                    if (vote_map.containsKey(VotesNewVoteType.APPROVE)) {
                        approval_count += vote_map.get(VotesNewVoteType.APPROVE);
                    }
                    if (vote_map.containsKey(VotesNewVoteType.OBJECT)) {
                        objection_count += vote_map.get(VotesNewVoteType.OBJECT);
                    }
                }
            }

            if (approval_count > objection_count) {
                passes_approval = true;
            }
        } else {
            // We only need approval for design or sprites - anything else is immediate pass
            passes_approval = true;
        }

        // Now we sum reviews
        if (passes_approval) {
            HashMap<Long, GHPullRequestReview> reviews = new HashMap<Long, GHPullRequestReview>();

            // But dont burn API calls if we dont need to
            for (GHPullRequestReview review : pr.prObject.listReviews()) {
                long review_author = review.getUser().getId();

                if (!reviewTeamMembers.contains(review_author)) {
                    // Skip - not in review team
                    continue;
                }

                // Now see if its the most recent
                if (reviews.containsKey(review_author)) {
                    GHPullRequestReview cached_review = reviews.get(review_author);

                    // If our cached review was made before our current one
                    if (cached_review.getCreatedAt().isBefore(review.getCreatedAt())) {
                        // Replace it
                        reviews.put(review_author, review);
                    }

                } else {
                    reviews.put(review_author, review);
                }
            }

            for (long review_author : reviews.keySet()) {
                GHPullRequestReview review = reviews.get(review_author);

                if (review.getState() == GHPullRequestReviewState.CHANGES_REQUESTED) {
                    passes_review = false;
                    break; // Anyone with requested changes fails it
                }

                if (review.getState() == GHPullRequestReviewState.APPROVED) {
                    passes_review = true;
                }
            }
        }

        // Now sum the labels
        if (passes_review) {
            if (pr.safeAddLabel(LABEL_AWAITING_MERGE)) {
                logger.info(
                        String.format("[PrLabelJob] [StatusManager] Labelling #%s as awaiting merge", pr.pullNumber));
            }
            pr.safeDelLabel(LABEL_AWAITING_REVIEW);
            pr.safeDelLabel(LABEL_AWAITING_APPROVAL);
            pr.safeDelLabel(LABEL_AWAITING_TYPE);
        } else {
            if (passes_approval) {
                pr.safeDelLabel(LABEL_AWAITING_MERGE);
                if (pr.safeAddLabel(LABEL_AWAITING_REVIEW)) {
                    logger.info(String.format("[PrLabelJob] [StatusManager] Labelling #%s as awaiting review",
                            pr.pullNumber));
                }
                pr.safeDelLabel(LABEL_AWAITING_APPROVAL);
                pr.safeDelLabel(LABEL_AWAITING_TYPE);
            } else {
                pr.safeDelLabel(LABEL_AWAITING_MERGE);
                pr.safeDelLabel(LABEL_AWAITING_REVIEW);
                if (pr.safeAddLabel(LABEL_AWAITING_APPROVAL)) {
                    logger.info(String.format("[PrLabelJob] [StatusManager] Labelling #%s as awaiting approval",
                            pr.pullNumber));
                }
                pr.safeDelLabel(LABEL_AWAITING_TYPE);
            }
        }
    }
}
