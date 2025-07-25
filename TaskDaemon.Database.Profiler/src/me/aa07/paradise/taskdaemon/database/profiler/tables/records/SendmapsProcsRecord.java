/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.profiler.tables.records;


import me.aa07.paradise.taskdaemon.database.profiler.tables.SendmapsProcs;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SendmapsProcsRecord extends UpdatableRecordImpl<SendmapsProcsRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_profilerdaemon.sendmaps_procs.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_profilerdaemon.sendmaps_procs.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>paradise_profilerdaemon.sendmaps_procs.procpath</code>.
     */
    public void setProcpath(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>paradise_profilerdaemon.sendmaps_procs.procpath</code>.
     */
    public String getProcpath() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return SendmapsProcs.SENDMAPS_PROCS.ID;
    }

    @Override
    public Field<String> field2() {
        return SendmapsProcs.SENDMAPS_PROCS.PROCPATH;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getProcpath();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getProcpath();
    }

    @Override
    public SendmapsProcsRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public SendmapsProcsRecord value2(String value) {
        setProcpath(value);
        return this;
    }

    @Override
    public SendmapsProcsRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SendmapsProcsRecord
     */
    public SendmapsProcsRecord() {
        super(SendmapsProcs.SENDMAPS_PROCS);
    }

    /**
     * Create a detached, initialised SendmapsProcsRecord
     */
    public SendmapsProcsRecord(Long id, String procpath) {
        super(SendmapsProcs.SENDMAPS_PROCS);

        setId(id);
        setProcpath(procpath);
    }
}
