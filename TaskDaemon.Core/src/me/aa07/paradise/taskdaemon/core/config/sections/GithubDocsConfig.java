package me.aa07.paradise.taskdaemon.core.config.sections;

import java.util.List;

public class GithubDocsConfig {
    public List<DocHolder> docs;

    public class DocHolder {
        public String apiKey;
        public String docKey;
        public String docPath;
        public String repoSlug;
    }
}
