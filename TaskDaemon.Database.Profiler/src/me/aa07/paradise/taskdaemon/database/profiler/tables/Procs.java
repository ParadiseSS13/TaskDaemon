/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.profiler.tables;


import java.util.Arrays;
import java.util.List;

import me.aa07.paradise.taskdaemon.database.profiler.Keys;
import me.aa07.paradise.taskdaemon.database.profiler.ParadiseProfilerdaemon;
import me.aa07.paradise.taskdaemon.database.profiler.tables.records.ProcsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
public class Procs extends TableImpl<ProcsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>paradise_profilerdaemon.procs</code>
     */
    public static final Procs PROCS = new Procs();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProcsRecord> getRecordType() {
        return ProcsRecord.class;
    }

    /**
     * The column <code>paradise_profilerdaemon.procs.id</code>.
     */
    public final TableField<ProcsRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>paradise_profilerdaemon.procs.procpath</code>.
     */
    public final TableField<ProcsRecord, String> PROCPATH = createField(DSL.name("procpath"), SQLDataType.VARCHAR(512).nullable(false), this, "");

    private Procs(Name alias, Table<ProcsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Procs(Name alias, Table<ProcsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_profilerdaemon.procs</code> table
     * reference
     */
    public Procs(String alias) {
        this(DSL.name(alias), PROCS);
    }

    /**
     * Create an aliased <code>paradise_profilerdaemon.procs</code> table
     * reference
     */
    public Procs(Name alias) {
        this(alias, PROCS);
    }

    /**
     * Create a <code>paradise_profilerdaemon.procs</code> table reference
     */
    public Procs() {
        this(DSL.name("procs"), null);
    }

    public <O extends Record> Procs(Table<O> child, ForeignKey<O, ProcsRecord> key) {
        super(child, key, PROCS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseProfilerdaemon.PARADISE_PROFILERDAEMON;
    }

    @Override
    public Identity<ProcsRecord, Long> getIdentity() {
        return (Identity<ProcsRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<ProcsRecord> getPrimaryKey() {
        return Keys.KEY_PROCS_PRIMARY;
    }

    @Override
    public List<UniqueKey<ProcsRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_PROCS_PROCPATH);
    }

    @Override
    public Procs as(String alias) {
        return new Procs(DSL.name(alias), this);
    }

    @Override
    public Procs as(Name alias) {
        return new Procs(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Procs rename(String name) {
        return new Procs(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Procs rename(Name name) {
        return new Procs(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
