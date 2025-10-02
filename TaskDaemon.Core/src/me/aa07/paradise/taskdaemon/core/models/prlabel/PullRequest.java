package me.aa07.paradise.taskdaemon.core.models.prlabel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import me.aa07.paradise.taskdaemon.database.pullrequests.enums.VotesNewVoteType;
import me.aa07.paradise.taskdaemon.database.pullrequests.enums.VotesNewVotingGroup;
import org.kohsuke.github.GHPullRequest;

public class PullRequest {
    public int pullNumber;
    public int testmergeRequests;
    public String prTypes;
    public GHPullRequest prObject;
    public HashMap<VotesNewVotingGroup, HashMap<VotesNewVoteType, Integer>> votes;
    public ArrayList<String> labels;

    public PullRequest() {
        votes = new HashMap<VotesNewVotingGroup, HashMap<VotesNewVoteType, Integer>>();
        labels = new ArrayList<String>();
    }

    public int sumTotalApprovals() {
        return sumTotalByVoteType(VotesNewVoteType.APPROVE);
    }

    public int sumTotalObjections() {
        return sumTotalByVoteType(VotesNewVoteType.OBJECT);
    }

    // Safety caching - stops eating API calls
    public boolean safeAddLabel(String label) throws IOException {
        if (labels.contains(label)) {
            return false;
        }

        labels.add(label);
        prObject.addLabels(label);
        return true;
    }

    public boolean safeDelLabel(String label) throws IOException {
        if (!labels.contains(label)) {
            return false;
        }

        labels.add(label);
        prObject.removeLabels(label);
        return true;
    }

    // Private methods
    private int sumTotalByVoteType(VotesNewVoteType voteType) {
        int total = 0;

        for (VotesNewVotingGroup group : votes.keySet()) {
            HashMap<VotesNewVoteType, Integer> vote_map = votes.get(group);

            if (vote_map.containsKey(voteType)) {
                total += vote_map.get(voteType);
            }
        }

        return total;
    }
}
