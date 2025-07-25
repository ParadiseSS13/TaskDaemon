/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables.records;


import me.aa07.paradise.taskdaemon.database.forums.tables.CalendarImportMap;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CalendarImportMapRecord extends UpdatableRecordImpl<CalendarImportMapRecord> implements Record4<Integer, Integer, Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_forums.calendar_import_map.import_id</code>.
     */
    public void setImportId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_forums.calendar_import_map.import_id</code>.
     */
    public Integer getImportId() {
        return (Integer) get(0);
    }

    /**
     * Setter for
     * <code>paradise_forums.calendar_import_map.import_feed_id</code>.
     */
    public void setImportFeedId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.calendar_import_map.import_feed_id</code>.
     */
    public Integer getImportFeedId() {
        return (Integer) get(1);
    }

    /**
     * Setter for
     * <code>paradise_forums.calendar_import_map.import_event_id</code>.
     */
    public void setImportEventId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.calendar_import_map.import_event_id</code>.
     */
    public Integer getImportEventId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>paradise_forums.calendar_import_map.import_guid</code>.
     */
    public void setImportGuid(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>paradise_forums.calendar_import_map.import_guid</code>.
     */
    public String getImportGuid() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Integer, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, Integer, Integer, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return CalendarImportMap.CALENDAR_IMPORT_MAP.IMPORT_ID;
    }

    @Override
    public Field<Integer> field2() {
        return CalendarImportMap.CALENDAR_IMPORT_MAP.IMPORT_FEED_ID;
    }

    @Override
    public Field<Integer> field3() {
        return CalendarImportMap.CALENDAR_IMPORT_MAP.IMPORT_EVENT_ID;
    }

    @Override
    public Field<String> field4() {
        return CalendarImportMap.CALENDAR_IMPORT_MAP.IMPORT_GUID;
    }

    @Override
    public Integer component1() {
        return getImportId();
    }

    @Override
    public Integer component2() {
        return getImportFeedId();
    }

    @Override
    public Integer component3() {
        return getImportEventId();
    }

    @Override
    public String component4() {
        return getImportGuid();
    }

    @Override
    public Integer value1() {
        return getImportId();
    }

    @Override
    public Integer value2() {
        return getImportFeedId();
    }

    @Override
    public Integer value3() {
        return getImportEventId();
    }

    @Override
    public String value4() {
        return getImportGuid();
    }

    @Override
    public CalendarImportMapRecord value1(Integer value) {
        setImportId(value);
        return this;
    }

    @Override
    public CalendarImportMapRecord value2(Integer value) {
        setImportFeedId(value);
        return this;
    }

    @Override
    public CalendarImportMapRecord value3(Integer value) {
        setImportEventId(value);
        return this;
    }

    @Override
    public CalendarImportMapRecord value4(String value) {
        setImportGuid(value);
        return this;
    }

    @Override
    public CalendarImportMapRecord values(Integer value1, Integer value2, Integer value3, String value4) {
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
     * Create a detached CalendarImportMapRecord
     */
    public CalendarImportMapRecord() {
        super(CalendarImportMap.CALENDAR_IMPORT_MAP);
    }

    /**
     * Create a detached, initialised CalendarImportMapRecord
     */
    public CalendarImportMapRecord(Integer importId, Integer importFeedId, Integer importEventId, String importGuid) {
        super(CalendarImportMap.CALENDAR_IMPORT_MAP);

        setImportId(importId);
        setImportFeedId(importFeedId);
        setImportEventId(importEventId);
        setImportGuid(importGuid);
    }
}
