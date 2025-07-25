/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables.records;


import me.aa07.paradise.taskdaemon.database.forums.tables.CoreTasksLog;

import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.TableRecordImpl;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreTasksLogRecord extends TableRecordImpl<CoreTasksLogRecord> implements Record4<ULong, Boolean, String, UInteger> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_forums.core_tasks_log.task</code>. Task ID
     * number
     */
    public void setTask(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_forums.core_tasks_log.task</code>. Task ID
     * number
     */
    public ULong getTask() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>paradise_forums.core_tasks_log.error</code>.
     */
    public void setError(Boolean value) {
        set(1, value);
    }

    /**
     * Getter for <code>paradise_forums.core_tasks_log.error</code>.
     */
    public Boolean getError() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>paradise_forums.core_tasks_log.log</code>.
     */
    public void setLog(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>paradise_forums.core_tasks_log.log</code>.
     */
    public String getLog() {
        return (String) get(2);
    }

    /**
     * Setter for <code>paradise_forums.core_tasks_log.time</code>.
     */
    public void setTime(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>paradise_forums.core_tasks_log.time</code>.
     */
    public UInteger getTime() {
        return (UInteger) get(3);
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<ULong, Boolean, String, UInteger> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<ULong, Boolean, String, UInteger> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return CoreTasksLog.CORE_TASKS_LOG.TASK;
    }

    @Override
    public Field<Boolean> field2() {
        return CoreTasksLog.CORE_TASKS_LOG.ERROR;
    }

    @Override
    public Field<String> field3() {
        return CoreTasksLog.CORE_TASKS_LOG.LOG;
    }

    @Override
    public Field<UInteger> field4() {
        return CoreTasksLog.CORE_TASKS_LOG.TIME;
    }

    @Override
    public ULong component1() {
        return getTask();
    }

    @Override
    public Boolean component2() {
        return getError();
    }

    @Override
    public String component3() {
        return getLog();
    }

    @Override
    public UInteger component4() {
        return getTime();
    }

    @Override
    public ULong value1() {
        return getTask();
    }

    @Override
    public Boolean value2() {
        return getError();
    }

    @Override
    public String value3() {
        return getLog();
    }

    @Override
    public UInteger value4() {
        return getTime();
    }

    @Override
    public CoreTasksLogRecord value1(ULong value) {
        setTask(value);
        return this;
    }

    @Override
    public CoreTasksLogRecord value2(Boolean value) {
        setError(value);
        return this;
    }

    @Override
    public CoreTasksLogRecord value3(String value) {
        setLog(value);
        return this;
    }

    @Override
    public CoreTasksLogRecord value4(UInteger value) {
        setTime(value);
        return this;
    }

    @Override
    public CoreTasksLogRecord values(ULong value1, Boolean value2, String value3, UInteger value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoreTasksLogRecord
     */
    public CoreTasksLogRecord() {
        super(CoreTasksLog.CORE_TASKS_LOG);
    }

    /**
     * Create a detached, initialised CoreTasksLogRecord
     */
    public CoreTasksLogRecord(ULong task, Boolean error, String log, UInteger time) {
        super(CoreTasksLog.CORE_TASKS_LOG);

        setTask(task);
        setError(error);
        setLog(log);
        setTime(time);
    }
}
