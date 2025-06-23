package me.aa07.paradise.taskdaemon.core.modules.aclcleanup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.aa07.paradise.taskdaemon.core.config.PfsenseConfig;
import me.aa07.paradise.taskdaemon.core.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.database.gamedb.Tables;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AclCleanupJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap datamap = context.getMergedJobDataMap();

        // Get our logger - important
        Object raw_logger = datamap.get("LOGGER");
        Optional<Logger> logger_holder = Optional.empty();

        if (raw_logger instanceof Logger l2) {
            logger_holder = Optional.of(l2);
        }

        if (!logger_holder.isPresent()) {
            System.out.println("[AclCleanup] LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
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
            logger.error("[AclCleanup] DBCORE WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        DbCore dbcore = dbcore_holder.get();

        Object raw_pfs_cfg = datamap.get("PFSENSE_CFG");
        Optional<PfsenseConfig> pfs_cfg_holder = Optional.empty();

        if (raw_pfs_cfg instanceof PfsenseConfig pfsCfg) {
            pfs_cfg_holder = Optional.of(pfsCfg);
        }

        if (pfs_cfg_holder.isEmpty()) {
            logger.error("[AclCleanup] PFSENSE_CFG WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        PfsenseConfig pfs_cfg = pfs_cfg_holder.get();

        try {
            List<String> current_acl_ips = fetchAcl(logger, pfs_cfg);
            logger.info("IPs in ACL ({}): {}", current_acl_ips.size(), String.join(" ", current_acl_ips));

            List<String> ips_to_remove = checkIpsInDatabase(current_acl_ips, logger, dbcore);

            if (ips_to_remove.isEmpty()) {
                logger.info("No IPs to remove from ACL");
            } else {
                logger.info("IPs to remove from ACL ({}): {}", ips_to_remove.size(), String.join(" ", ips_to_remove));

                for (String ip : ips_to_remove) {
                    removeIpFromAcl(ip, logger, pfs_cfg);
                }

                logger.info("Removed {} IPs from ACL", ips_to_remove.size());
            }

            List<String> updated_acl_ips = fetchAcl(logger, pfs_cfg);
            logger.info("IPs now in ACL ({}): {}", updated_acl_ips.size(), String.join(" ", updated_acl_ips));

        } catch (IOException e) {
            logger.error("Error in AclCleanupJob!");
            logger.error(e);
        }
    }

    public void removeIpFromAcl(String ip, Logger logger, PfsenseConfig cfg) throws IOException {
        logger.info("Removing {} from ACL", ip);

        Socket socket = new Socket(cfg.host, cfg.port);
        OutputStream out = socket.getOutputStream();

        String command = "del acl #1 " + ip + "\n";
        out.write(command.getBytes("ascii"));

        socket.close();
    }

    public List<String> fetchAcl(Logger logger, PfsenseConfig cfg) throws IOException {
        List<String> ip_list = new ArrayList<>();

        Socket socket = new Socket("10.0.0.1", 7542);
        socket.setSoTimeout(10000);

        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        String command = "show acl #1\n";
        out.write(command.getBytes("ascii"));

        StringBuilder retval = new StringBuilder();
        byte[] buffer = new byte[16];
        int bytes_read;

        while ((bytes_read = in.read(buffer)) != -1) {
            retval.append(new String(buffer, 0, bytes_read));
        }

        socket.close();

        String[] raw_ips = retval.toString().split("\n");
        for (String ip : raw_ips) {
            if (ip.trim().isEmpty()) {
                continue;
            }

            String[] parts = ip.split(" ");
            if (parts.length >= 2) {
                ip_list.add(parts[1]);
            }
        }

        return ip_list;
    }

    public List<String> checkIpsInDatabase(List<String> ips, Logger logger, DbCore dbcore) {
        List<String> to_remove = new ArrayList<>();

        DSLContext ctx = dbcore.jooq(DatabaseType.GameDb);
        LocalDateTime ten_mins_ago = dbcore.now().minusMinutes(10);

        for (String ip : ips) {
            if (!ctx.fetchExists(ctx.select(Tables.PLAYER.CKEY).from(Tables.PLAYER).where(Tables.PLAYER.IP.eq(ip)))) {
                logger.info("IP {} no longer in database.", ip);
                to_remove.add(ip);
                continue;
            }

            boolean seen_in_last_10m = ctx.fetchExists(
                ctx.select(Tables.PLAYER.CKEY).from(Tables.PLAYER).where(Tables.PLAYER.IP.eq(ip))
                    .and(Tables.PLAYER.LASTSEEN.lt(ten_mins_ago))
            );

            if (!seen_in_last_10m) {
                logger.info("IP {} not active within last 10 minutes.", ip);
                to_remove.add(ip);
            }
        }

        return to_remove;
    }
}
