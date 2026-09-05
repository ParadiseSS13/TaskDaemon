package me.aa07.paradise.taskdaemon.core.services.database;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import javax.sql.DataSource;
import me.aa07.paradise.taskdaemon.core.config.ConfigHolder;
import me.aa07.paradise.taskdaemon.core.config.sections.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.NoDataFoundException;
import org.jooq.impl.DSL;

public class DbCore {
    private HashMap<DatabaseType, Pair<DataSource, SQLDialect>> connectionMap;

    public DbCore(ConfigHolder config, Logger logger) {
        // Suppress JOOQ console spam
        System.getProperties().setProperty("org.jooq.no-logo", "true");
        System.getProperties().setProperty("org.jooq.no-tips", "true");

        connectionMap = new HashMap<DatabaseType, Pair<DataSource, SQLDialect>>();

        establishConnections(config);
        logger.info("Ready to handle DB requests");
    }

    private DataSource openMySqlDataSource(String url, String username, String password) {
        BasicDataSource source = new BasicDataSource();
        source.addConnectionProperty("autoReconnect", "true");
        source.addConnectionProperty("allowMultiQueries", "true");
        source.addConnectionProperty("zeroDateTimeBehavior", "convertToNull");
        source.addConnectionProperty("connectionTimeZone", "UTC");
        source.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl(url);
        source.setUsername(username);
        source.setPassword(password);
        source.setMaxTotal(8);
        source.setMaxIdle(8);
        source.setTimeBetweenEvictionRunsMillis(180 * 1000);
        source.setSoftMinEvictableIdleTimeMillis(180 * 1000);

        return source;
    }

    private DataSource openPostgresDataSource(String url, String username, String password) {
        BasicDataSource source = new BasicDataSource();
        source.addConnectionProperty("autoReconnect", "true");
        source.addConnectionProperty("allowMultiQueries", "true");
        source.addConnectionProperty("zeroDateTimeBehavior", "convertToNull");
        source.addConnectionProperty("connectionTimeZone", "UTC");
        source.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        source.setDriverClassName("org.postgresql.Driver");
        source.setUrl(url);
        source.setUsername(username);
        source.setPassword(password);
        source.setMaxTotal(8);
        source.setMaxIdle(8);
        source.setTimeBetweenEvictionRunsMillis(180 * 1000);
        source.setSoftMinEvictableIdleTimeMillis(180 * 1000);

        return source;
    }

    private void establishConnections(ConfigHolder config) {
        HashMap<DatabaseType, DatabaseConfig> mysql_db_types = new HashMap<DatabaseType, DatabaseConfig>();

        mysql_db_types.put(DatabaseType.Automation, config.automationDatabase);
        mysql_db_types.put(DatabaseType.Forums, config.forumsDatabase);
        mysql_db_types.put(DatabaseType.GameDb, config.gameDatabase);
        mysql_db_types.put(DatabaseType.ProfilerDb, config.profilerDatabase);
        mysql_db_types.put(DatabaseType.PullRequests, config.pullRequestsDatabase);

        for (DatabaseType dbtype : mysql_db_types.keySet()) {
            DatabaseConfig cfg = mysql_db_types.get(dbtype);
            DataSource ds = openMySqlDataSource(String.format("jdbc:mysql://%s/%s", cfg.host, cfg.database), cfg.username, cfg.password);
            connectionMap.put(dbtype, Pair.of(ds, SQLDialect.MYSQL));
        }

        // And do special stuff for Postgres
        DatabaseConfig adb = config.authentikDatabase;
        DataSource authentik_ds = openPostgresDataSource(String.format("jdbc:postgresql://%s/%s", adb.host, adb.database), adb.username, adb.password);
        connectionMap.put(DatabaseType.Authentik, Pair.of(authentik_ds, SQLDialect.POSTGRES));
    }

    // Get a DSL context
    public DSLContext jooq(DatabaseType type) {
        if (!connectionMap.containsKey(type)) {
            throw new NoDataFoundException("Supplied type key not present in connection map");
        }

        Pair<DataSource, SQLDialect> ds_pair = connectionMap.get(type);

        return DSL.using(ds_pair.getLeft(), ds_pair.getRight());
    }

    // Easy way for NOW() in SQL
    public LocalDateTime now() {
        return new Timestamp(new Date().getTime()).toLocalDateTime();
    }

}
