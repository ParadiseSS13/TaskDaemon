package me.aa07.paradise.taskdaemon.core.config;

import me.aa07.paradise.taskdaemon.core.config.sections.AuthentikConfig;
import me.aa07.paradise.taskdaemon.core.config.sections.DatabaseConfig;
import me.aa07.paradise.taskdaemon.core.config.sections.GithubConfig;
import me.aa07.paradise.taskdaemon.core.config.sections.GithubDocsConfig;
import me.aa07.paradise.taskdaemon.core.config.sections.InvisionConfig;
import me.aa07.paradise.taskdaemon.core.config.sections.Ip2AsnSerivceConfig;
import me.aa07.paradise.taskdaemon.core.config.sections.PatreonConfig;
import me.aa07.paradise.taskdaemon.core.config.sections.PfsenseConfig;
import me.aa07.paradise.taskdaemon.core.config.sections.RedisConfig;
import me.aa07.paradise.taskdaemon.core.config.sections.TgsConfig;

public class ConfigHolder {
    public AuthentikConfig authentikApi;
    public DatabaseConfig authentikDatabase;
    public DatabaseConfig automationDatabase;
    public DatabaseConfig forumsDatabase;
    public DatabaseConfig gameDatabase;
    public GithubConfig github;
    public GithubDocsConfig githubDocs;
    public InvisionConfig invisionConfig;
    public Ip2AsnSerivceConfig ip2asn;
    public PatreonConfig patreon;
    public PfsenseConfig pfsense;
    public DatabaseConfig profilerDatabase;
    public DatabaseConfig pullRequestsDatabase;
    public RedisConfig redis;
    public TgsConfig tgs;
}
