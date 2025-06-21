package me.aa07.paradise.taskdaemon.core.modules.ip2asn;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.aa07.paradise.taskdaemon.core.config.Ip2AsnSerivceConfig;
import me.aa07.paradise.taskdaemon.core.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.core.models.ip2asn.Ip2AsnResponseModel;
import me.aa07.paradise.taskdaemon.database.gamedb.Tables;
import me.aa07.paradise.taskdaemon.database.gamedb.tables.records.Ip2groupRecord;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Select;
import org.jooq.types.UInteger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution // NO
public class Ip2AsnJob implements Job {

    @Override
    public void execute(JobExecutionContext event) throws JobExecutionException {
        JobDataMap datamap = event.getMergedJobDataMap();

        // Get our logger - important
        Object raw_logger = datamap.get("LOGGER");
        Optional<Logger> logger_holder = Optional.empty();

        if (raw_logger instanceof Logger l2) {
            logger_holder = Optional.of(l2);
        }

        if (!logger_holder.isPresent()) {
            System.out.println("[Ip2Asn] LOGGER WAS SOMEHOW NULL - THIS IS VERY BAD");
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
            logger.error("[Ip2Asn] DBCORE WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        DbCore dbcore = dbcore_holder.get();

        // Now get our config
        Object raw_ip2asn_cfg = datamap.get("IP2ASNCFG");
        Optional<Ip2AsnSerivceConfig> ip2asn_cfg_holder = Optional.empty();

        if (raw_ip2asn_cfg instanceof Ip2AsnSerivceConfig ip2asnCfg2) {
            ip2asn_cfg_holder = Optional.of(ip2asnCfg2);
        }

        if (!ip2asn_cfg_holder.isPresent()) {
            logger.error("[Ip2Asn] IP2ASNCFG WAS SOMEHOW NULL - THIS IS VERY BAD");
            return;
        }

        Ip2AsnSerivceConfig config = ip2asn_cfg_holder.get();

        DSLContext ctx = dbcore.jooq(DatabaseType.GameDb);

        // So
        // JOOQ doesnt have INET_ATON, presumably because its a MySQL native
        // So we have to do the manual selects then work out the differences
        // To do this we need:
        // 1. All IPs from player seen in the last 30 days
        // 2. Then remove the IPs in ip2group that have been updated in the last 7 days
        // Then we need to update IP2Group or insert where required
        LocalDateTime last_30_days = LocalDateTime.now().minusDays(30);
        LocalDateTime last_7_days = LocalDateTime.now().minusDays(7);

        logger.info("[Ip2Asn] Pulling player IPs...");

        // Get the player IPs
        List<String> player_ips = ctx.select(Tables.PLAYER.IP).from(Tables.PLAYER)
                .where(Tables.PLAYER.LASTSEEN.gt(last_30_days)).fetch()
                .getValues(Tables.PLAYER.IP, String.class);

        logger.info(String.format("[Ip2Asn] Pulled %s player IPs, now pulling ip2group IPs...", player_ips.size()));

        List<UInteger> ip2group_ips = ctx.select(Tables.IP2GROUP.IP).from(Tables.IP2GROUP)
                .where(Tables.IP2GROUP.DATE.gt(last_7_days)).fetch()
                .getValues(Tables.IP2GROUP.IP, UInteger.class);

        logger.info(String.format("[Ip2Asn] Pulled %s ip2group IPs, now parsing...", ip2group_ips.size()));

        // Now turn that to regular IPs
        ArrayList<String> parsed_ip2group_ips = new ArrayList<String>();

        for (UInteger ip_uint : ip2group_ips) {
            String ip_str = uint2ip(ip_uint);
            parsed_ip2group_ips.add(ip_str);
        }

        ArrayList<String> ips_to_get = new ArrayList<String>();

        // Start with the IPs in player - then see if they exist in the last 7 days in
        // ip2group
        for (String ip : player_ips) {
            if (!parsed_ip2group_ips.contains(ip)) {
                ips_to_get.add(ip);
            }
        }

        logger.info(String.format("[Ip2Asn] Found %s IPs to refresh groups for", ips_to_get.size()));

        if (ips_to_get.size() == 0) {
            logger.info("[Ip2Asn] No work to do");
            return;
        }

        // Now do the big refresh
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
        for (String ip : ips_to_get) {
            try {
                // Get our AS - probably a more elegant way to do this but meh
                HttpRequest httpreq = HttpRequest.newBuilder().uri(new URI(String.format("%s%s", config.host, ip)))
                        .GET().build();

                // Send the request off
                HttpResponse<String> response = client.send(httpreq, BodyHandlers.ofString());

                // Decode the shenanigans
                Ip2AsnResponseModel res_model = gson.fromJson(response.body(), Ip2AsnResponseModel.class);
                UInteger ip_uint = ip2uint(ip);
                UInteger asn = UInteger.valueOf(res_model.asn);

                // See if our row exists, we update if it does
                Select<Record1<UInteger>> fetch_test = ctx.select(Tables.IP2GROUP.IP).from(Tables.IP2GROUP)
                        .where(Tables.IP2GROUP.IP.eq(ip_uint));

                if (ctx.fetchExists(fetch_test)) {
                    // It exists, update
                    logger.info(String.format("[Ip2Asn] Updating row for %s", ip));
                    ctx.update(Tables.IP2GROUP)
                            .set(Tables.IP2GROUP.GROUPSTR, asn)
                            .set(Tables.IP2GROUP.DATE, dbcore.now())
                            .where(Tables.IP2GROUP.IP.eq(ip_uint))
                            .execute();
                } else {
                    // It dont exist, insert
                    logger.info(String.format("[Ip2Asn] Inserting row for %s", ip));
                    Ip2groupRecord record = ctx.newRecord(Tables.IP2GROUP);
                    record.setIp(ip_uint);
                    record.setDate(dbcore.now());
                    record.setGroupstr(asn);
                    record.store();
                }

            } catch (Exception e) {
                logger.warn(String.format("[Ip2Asn] Failed to get AS info for IP %s - skipping", ip));
                logger.warn(e);
            }
        }

        logger.info("[Ip2Asn] Processing complete");
    }

    // Turns a uint IP into its string form
    private String uint2ip(UInteger ip) {
        long ip_long = ip.longValue();
        return String.format("%d.%d.%d.%d",
                (ip_long >> 24 & 0xff),
                (ip_long >> 16 & 0xff),
                (ip_long >> 8 & 0xff),
                (ip_long & 0xff));
    }

    // Turns a string IP into its uint form
    private UInteger ip2uint(String ip) {
        String[] octets = ip.split("\\.");

        long ip_long = 0;
        for (int i = 0; i < 4; i++) {
            int octet = Integer.parseInt(octets[i]);
            if (octet < 0 || octet > 255) {
                throw new IllegalArgumentException("Invalid octet in IP address: " + octets[i]);
            }
            // This does the shift magic with the for loop,
            // with each iteration shifting by 8
            ip_long |= ((long) octet << (24 - 8 * i));
        }

        return UInteger.valueOf(ip_long);
    }
}
