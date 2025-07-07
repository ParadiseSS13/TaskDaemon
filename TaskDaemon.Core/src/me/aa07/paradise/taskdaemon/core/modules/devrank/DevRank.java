package me.aa07.paradise.taskdaemon.core.modules.devrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import me.aa07.paradise.taskdaemon.core.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.database.forums.Tables;
import me.aa07.paradise.taskdaemon.database.gamedb.tables.Admin;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Scheduled job that syncs dev team permissions between forums and game databases.
 */
@SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength"})
public class DevRank implements Job {
    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:JavadocVariable"})
    private static final int DEV_TEAM_GROUP = 39;
    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:JavadocVariable"})
    private static final int DEV_TEAM_BITFLAG = 262144;

    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength", "checkstyle:LocalVariableName", "checkstyle:FinalParameters", "checkstyle:DesignForExtension"})
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();

        // Get logger and db
        Logger logger = (Logger) dataMap.get("LOGGER");
        DbCore dbcore = (DbCore) dataMap.get("DBCORE");

        if (logger == null || dbcore == null) {
            System.err.println("[DevRank] LOGGER or DBCORE was not set in JobDataMap.");
            return;
        }

        logger.info("[DevRank] Starting dev rank sync");

        try {
            DSLContext forumsDb = dbcore.jooq(DatabaseType.Forums);
            DSLContext gameDb = dbcore.jooq(DatabaseType.GameDb);

            // Collect all dev team ckeys from forums database
            List<String> dev_team_ckeys = new ArrayList<>();

            Result<? extends Record> forumRecords = forumsDb.select(
                            Tables.CORE_MEMBERS.MEMBER_ID,
                            Tables.CORE_MEMBERS.NAME,
                            Tables.CORE_MEMBERS.MEMBER_GROUP_ID,
                            Tables.CORE_MEMBERS.MGROUP_OTHERS,
                            Tables.CORE_PFIELDS_CONTENT.FIELD_10
                    )
                    .from(Tables.CORE_MEMBERS)
                    .leftJoin(Tables.CORE_PFIELDS_CONTENT)
                    .on(Tables.CORE_MEMBERS.MEMBER_ID.eq(Tables.CORE_PFIELDS_CONTENT.MEMBER_ID))
                    .fetch();

            for (Record rec : forumRecords) {
                int primaryGroup = rec.get(Tables.CORE_MEMBERS.MEMBER_GROUP_ID);
                String otherGroups = rec.get(Tables.CORE_MEMBERS.MGROUP_OTHERS);
                String ckey = rec.get(Tables.CORE_PFIELDS_CONTENT.FIELD_10);

                Set<Integer> all_groups = new HashSet<Integer>();
                all_groups.add(primaryGroup);

                if (otherGroups != null && !otherGroups.isBlank()) {
                    Arrays.stream(otherGroups.split(","))
                            .filter(g -> !g.isBlank())
                            .map(Integer::parseInt)
                            .forEach(all_groups::add);
                }

                if (all_groups.contains(DEV_TEAM_GROUP)) {
                    if (ckey == null || ckey.isBlank()) {
                        logger.warn("[DevRank] Forums user {} (ID {}) has no linked ckey", rec.get(Tables.CORE_MEMBERS.NAME), rec.get(Tables.CORE_MEMBERS.MEMBER_ID));
                        continue;
                    }

                    String cleaned = ckey.toLowerCase().replaceAll("[\\s_\\-]", "");
                    dev_team_ckeys.add(cleaned);
                }
            }

            // Load all ingame admins
            Map<String, AdminEntry> ingameAdmins = new HashMap<>();

            Result<? extends Record> adminRecords = gameDb.select(
                    Admin.ADMIN.ID,
                    Admin.ADMIN.CKEY,
                    Admin.ADMIN.ADMIN_RANK,
                    Admin.ADMIN.FLAGS
            ).from(Admin.ADMIN).fetch();

            for (Record rec : adminRecords) {
                String ckey = rec.get(Admin.ADMIN.CKEY);
                ingameAdmins.put(ckey, new AdminEntry(
                        rec.get(Admin.ADMIN.ID),
                        rec.get(Admin.ADMIN.ADMIN_RANK),
                        rec.get(Admin.ADMIN.FLAGS)
                ));
            }

            // Apply permissions to those who need them
            for (String ckey : dev_team_ckeys) {
                AdminEntry entry = ingameAdmins.get(ckey);

                if (entry == null) {
                    logger.info("[DevRank] Ckey {} not in admin table, adding new dev", ckey);
                    gameDb.insertInto(Admin.ADMIN)
                            .set(Admin.ADMIN.CKEY, ckey)
                            .set(Admin.ADMIN.ADMIN_RANK, "Developer")
                            .set(Admin.ADMIN.FLAGS, DEV_TEAM_BITFLAG)
                            .execute();
                } else {
                    if ("Removed".equals(entry.rank()) || entry.flags() == 0) {
                        logger.info("[DevRank] Resetting {} to dev team with new flag", ckey);
                        gameDb.update(Admin.ADMIN)
                                .set(Admin.ADMIN.ADMIN_RANK, "Developer")
                                .set(Admin.ADMIN.FLAGS, DEV_TEAM_BITFLAG)
                                .where(Admin.ADMIN.ID.eq(entry.id()))
                                .execute();
                    } else if ((entry.flags() & DEV_TEAM_BITFLAG) == 0) {
                        logger.info("[DevRank] Adding dev flag to {}", ckey);
                        gameDb.update(Admin.ADMIN)
                                .set(Admin.ADMIN.FLAGS, entry.flags() + DEV_TEAM_BITFLAG)
                                .where(Admin.ADMIN.ID.eq(entry.id()))
                                .execute();
                    } else {
                        logger.debug("[DevRank] {} already has dev flag", ckey);
                    }
                }
            }

            // Remove devteam flag from those who no longer qualify
            for (Map.Entry<String, AdminEntry> entry : ingameAdmins.entrySet()) {
                String ckey = entry.getKey();
                AdminEntry admin = entry.getValue();

                if (!dev_team_ckeys.contains(ckey)) {
                    if (admin.flags() == DEV_TEAM_BITFLAG) {
                        logger.info("[DevRank] {} only had dev flag, removing rank and flags", ckey);
                        gameDb.update(Admin.ADMIN)
                                .set(Admin.ADMIN.ADMIN_RANK, "Removed")
                                .set(Admin.ADMIN.FLAGS, 0)
                                .where(Admin.ADMIN.ID.eq(admin.id()))
                                .execute();
                    } else if ((admin.flags() & DEV_TEAM_BITFLAG) != 0) {
                        logger.info("[DevRank] {} no longer in dev team, removing dev flag only", ckey);
                        gameDb.update(Admin.ADMIN)
                                .set(Admin.ADMIN.FLAGS, admin.flags() - DEV_TEAM_BITFLAG)
                                .where(Admin.ADMIN.ID.eq(admin.id()))
                                .execute();
                    }
                }
            }

            logger.info("[DevRank] Finished sync, {} devteam users processed", dev_team_ckeys.size());

        } catch (Exception e) {
            logger.error("[DevRank] Error during sync", e);
            throw new JobExecutionException(e);
        }
    }

    @SuppressWarnings("checkstyle:Indentation")
    private record AdminEntry(int id, String rank, int flags) {
    }
}
