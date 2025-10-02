package me.aa07.paradise.taskdaemon.core.modules.prlabel;

import java.io.IOException;
import me.aa07.paradise.taskdaemon.core.models.prlabel.PullRequest;
import org.apache.logging.log4j.Logger;
import org.kohsuke.github.GHPullRequestFileDetail;

public class FilesChangedManager implements IPullRequestProcessor {
    private Logger logger;

    public FilesChangedManager(Logger logger) {
        this.logger = logger;
    }

    // Dont change these
    private static final String LABEL_CONFIG = "Configuration Change";
    private static final String LABEL_MAP_EDIT = "Map Edit";
    private static final String LABEL_RUSTLIBS = "Rustlibs";
    private static final String LABEL_SOUND = "Sound";
    private static final String LABEL_SPRITES = "Sprites";
    private static final String LABEL_SQL = "SQL Change";
    private static final String LABEL_TGUI = "TGUI";

    @Override
    public void processPr(PullRequest pr) throws IOException {
        for (GHPullRequestFileDetail fd : pr.prObject.listFiles()) {
            internalProcessPr(pr, fd, LABEL_CONFIG, "config/");
            internalProcessPr(pr, fd, LABEL_MAP_EDIT, "_maps/map_files/");
            internalProcessPr(pr, fd, LABEL_RUSTLIBS, "rust/");
            internalProcessPr(pr, fd, LABEL_SOUND, "sound/");
            internalProcessPr(pr, fd, LABEL_SPRITES, "icons/");
            internalProcessPr(pr, fd, LABEL_SQL, "SQL/");
            internalProcessPr(pr, fd, LABEL_TGUI, "tgui/");
        }
    }

    private void internalProcessPr(PullRequest pr, GHPullRequestFileDetail fd, String label, String path)
            throws IOException {
        if (fd.getFilename().startsWith(path)) {
            if (pr.safeAddLabel(label)) {
                logger.info(
                        String.format("[PrLabelJob] [FilesChangedManager] Adding '%s' to #%s", label, pr.pullNumber));
            }
        }
    }
}
