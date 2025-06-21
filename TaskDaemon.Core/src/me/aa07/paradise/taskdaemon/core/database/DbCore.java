package me.aa07.paradise.taskdaemon.core.database;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import javax.sql.DataSource;
import me.aa07.paradise.taskdaemon.core.config.ConfigHolder;
import me.aa07.paradise.taskdaemon.core.config.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.NoDataFoundException;
import org.jooq.impl.DSL;

public class DbCore {
    private HashMap<DatabaseType, DataSource> connectionMap;

    public DbCore(ConfigHolder config, Logger logger) {
        // Suppress JOOQ console spam
        System.getProperties().setProperty("org.jooq.no-logo", "true");
        System.getProperties().setProperty("org.jooq.no-tips", "true");

        connectionMap = new HashMap<DatabaseType, DataSource>();

        establishConnections(config);
        logger.info("Ready to handle DB requests");
    }

    private DataSource openDataSource(String url, String username, String password) {
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

    private void establishConnections(ConfigHolder config) {
        HashMap<DatabaseType, DatabaseConfig> db_types = new HashMap<DatabaseType, DatabaseConfig>();

        db_types.put(DatabaseType.GameDb, config.gameDatabase);
        db_types.put(DatabaseType.ProfilerDb, config.profilerDatabase);

        for (DatabaseType dbtype : db_types.keySet()) {
            DatabaseConfig cfg = db_types.get(dbtype);
            DataSource ds = openDataSource(String.format("jdbc:mysql://%s/%s", cfg.host, cfg.database), cfg.username, cfg.password);
            connectionMap.put(dbtype, ds);
        }

    }

    // Get a DSL context
    public DSLContext jooq(DatabaseType type) {
        if (!connectionMap.containsKey(type)) {
            throw new NoDataFoundException("Supplied type key not present in connection map");
        }

        return DSL.using(connectionMap.get(type), SQLDialect.MYSQL);
    }

    // Easy way for NOW() in SQL
    public LocalDateTime now() {
        return new Timestamp(new Date().getTime()).toLocalDateTime();
    }

}
