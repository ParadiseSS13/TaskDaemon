package me.aa07.paradise.taskdaemon.core.modules.prlabel;

import java.io.IOException;
import me.aa07.paradise.taskdaemon.core.models.prlabel.PullRequest;

public interface IPullRequestProcessor {
    public void processPr(PullRequest pr) throws IOException;
}
