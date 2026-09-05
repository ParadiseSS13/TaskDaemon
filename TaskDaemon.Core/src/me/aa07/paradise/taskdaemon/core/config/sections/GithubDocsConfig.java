package me.aa07.paradise.taskdaemon.core.config.sections;

import java.util.List;

public class GithubDocsConfig {
    public List<RepoHolder> repositories;

    public class RepoHolder {
        public String slug;
        public String apiKey;
        public List<DocHolder> docs;

        public class DocHolder {
            public String docKey;
            public String docPath;
        }
    }
}
