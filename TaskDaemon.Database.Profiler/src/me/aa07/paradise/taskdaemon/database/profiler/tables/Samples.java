/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.profiler.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import me.aa07.paradise.taskdaemon.database.profiler.Keys;
import me.aa07.paradise.taskdaemon.database.profiler.ParadiseProfilerdaemon;
import me.aa07.paradise.taskdaemon.database.profiler.tables.records.SamplesRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row9;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Samples extends TableImpl<SamplesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>paradise_profilerdaemon.samples</code>
     */
    public static final Samples SAMPLES = new Samples();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SamplesRecord> getRecordType() {
        return SamplesRecord.class;
    }

    /**
     * The column <code>paradise_profilerdaemon.samples.id</code>.
     */
    public final TableField<SamplesRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>paradise_profilerdaemon.samples.roundId</code>.
     */
    public final TableField<SamplesRecord, Integer> ROUNDID = createField(DSL.name("roundId"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>paradise_profilerdaemon.samples.sampleTime</code>.
     */
    public final TableField<SamplesRecord, LocalDateTime> SAMPLETIME = createField(DSL.name("sampleTime"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field("current_timestamp()", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>paradise_profilerdaemon.samples.procId</code>.
     */
    public final TableField<SamplesRecord, Long> PROCID = createField(DSL.name("procId"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>paradise_profilerdaemon.samples.self</code>.
     */
    public final TableField<SamplesRecord, Double> SELF = createField(DSL.name("self"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>paradise_profilerdaemon.samples.total</code>.
     */
    public final TableField<SamplesRecord, Double> TOTAL = createField(DSL.name("total"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>paradise_profilerdaemon.samples.real</code>.
     */
    public final TableField<SamplesRecord, Double> REAL = createField(DSL.name("real"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>paradise_profilerdaemon.samples.over</code>.
     */
    public final TableField<SamplesRecord, Double> OVER = createField(DSL.name("over"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>paradise_profilerdaemon.samples.calls</code>.
     */
    public final TableField<SamplesRecord, Integer> CALLS = createField(DSL.name("calls"), SQLDataType.INTEGER.nullable(false), this, "");

    private Samples(Name alias, Table<SamplesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Samples(Name alias, Table<SamplesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_profilerdaemon.samples</code> table
     * reference
     */
    public Samples(String alias) {
        this(DSL.name(alias), SAMPLES);
    }

    /**
     * Create an aliased <code>paradise_profilerdaemon.samples</code> table
     * reference
     */
    public Samples(Name alias) {
        this(alias, SAMPLES);
    }

    /**
     * Create a <code>paradise_profilerdaemon.samples</code> table reference
     */
    public Samples() {
        this(DSL.name("samples"), null);
    }

    public <O extends Record> Samples(Table<O> child, ForeignKey<O, SamplesRecord> key) {
        super(child, key, SAMPLES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseProfilerdaemon.PARADISE_PROFILERDAEMON;
    }

    @Override
    public Identity<SamplesRecord, Long> getIdentity() {
        return (Identity<SamplesRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<SamplesRecord> getPrimaryKey() {
        return Keys.KEY_SAMPLES_PRIMARY;
    }

    @Override
    public List<ForeignKey<SamplesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK1_PROCID_PROCS_ID);
    }

    private transient Procs _procs;

    public Procs procs() {
        if (_procs == null)
            _procs = new Procs(this, Keys.FK1_PROCID_PROCS_ID);

        return _procs;
    }

    @Override
    public Samples as(String alias) {
        return new Samples(DSL.name(alias), this);
    }

    @Override
    public Samples as(Name alias) {
        return new Samples(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Samples rename(String name) {
        return new Samples(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Samples rename(Name name) {
        return new Samples(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Long, Integer, LocalDateTime, Long, Double, Double, Double, Double, Integer> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}
