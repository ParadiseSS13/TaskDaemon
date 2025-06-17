package me.aa07.paradise.taskdaemon.core.modules.profileringest.processors;

import com.google.gson.reflect.TypeToken;
import java.util.List;
import me.aa07.paradise.taskdaemon.core.database.DatabaseType;
import me.aa07.paradise.taskdaemon.core.database.DbCore;
import me.aa07.paradise.taskdaemon.core.models.profiler.ProfilerHolder;
import me.aa07.paradise.taskdaemon.core.models.profiler.SendmapsProcData;
import me.aa07.paradise.taskdaemon.database.profiler.Tables;
import me.aa07.paradise.taskdaemon.database.profiler.tables.records.SendmapsProcsRecord;
import me.aa07.paradise.taskdaemon.database.profiler.tables.records.SendmapsSamplesRecord;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;

public class ProfilerSendmapsProcessor extends ProfilerBaseProcessor {
    public ProfilerSendmapsProcessor(DbCore database, Logger logger) {
        super(database, logger, "SendmapsProcessor");
    }

    @Override
    protected void doProcessing(ProfilerHolder holder) {
        List<SendmapsProcData> proc_list = gson.fromJson(
            holder.sendmapsData, new TypeToken<List<SendmapsProcData>>() {}.getType()
        );

        log(String.format("Procs logged: %s", proc_list.size()));

        for (SendmapsProcData procdata : proc_list) {
            long proc_id = getProcId(procdata.name);
            SendmapsSamplesRecord sr = database.jooq(DatabaseType.ProfilerDb).newRecord(Tables.SENDMAPS_SAMPLES);
            sr.setRoundid(holder.roundId);
            sr.setSampletime(database.now());
            sr.setProcid(proc_id);
            sr.setValue(procdata.value);
            sr.setCalls(procdata.calls);
            sr.store();
        }
    }

    @Override
    protected long getProcId(String procname) {
        DSLContext ctx = database.jooq(DatabaseType.GameDb);
        if (!ctx.fetchExists(ctx.select(Tables.SENDMAPS_PROCS.ID).from(Tables.SENDMAPS_PROCS).where(Tables.SENDMAPS_PROCS.PROCPATH.eq(procname)))) {
            // We dont exist, make us
            log(String.format("%s did not exist in the DB. It does now.", procname));

            SendmapsProcsRecord record = ctx.newRecord(Tables.SENDMAPS_PROCS);
            record.setProcpath(procname);
            record.store();
            return record.getId();
        }

        // We do exist, just grab
        return ctx.select(Tables.SENDMAPS_PROCS.ID).from(Tables.SENDMAPS_PROCS).where(Tables.SENDMAPS_PROCS.PROCPATH.eq(procname)).fetchOne().value1();
    }
}
