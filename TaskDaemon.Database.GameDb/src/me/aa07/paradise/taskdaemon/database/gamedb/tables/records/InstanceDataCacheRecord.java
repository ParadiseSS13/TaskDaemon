/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.gamedb.tables.records;


import java.time.LocalDateTime;

import me.aa07.paradise.taskdaemon.database.gamedb.tables.InstanceDataCache;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class InstanceDataCacheRecord extends UpdatableRecordImpl<InstanceDataCacheRecord> implements Record4<String, String, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_gamedb.instance_data_cache.server_id</code>.
     */
    public void setServerId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_gamedb.instance_data_cache.server_id</code>.
     */
    public String getServerId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>paradise_gamedb.instance_data_cache.key_name</code>.
     */
    public void setKeyName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>paradise_gamedb.instance_data_cache.key_name</code>.
     */
    public String getKeyName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>paradise_gamedb.instance_data_cache.key_value</code>.
     */
    public void setKeyValue(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>paradise_gamedb.instance_data_cache.key_value</code>.
     */
    public String getKeyValue() {
        return (String) get(2);
    }

    /**
     * Setter for <code>paradise_gamedb.instance_data_cache.last_updated</code>.
     */
    public void setLastUpdated(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>paradise_gamedb.instance_data_cache.last_updated</code>.
     */
    public LocalDateTime getLastUpdated() {
        return (LocalDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, String, String, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<String, String, String, LocalDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return InstanceDataCache.INSTANCE_DATA_CACHE.SERVER_ID;
    }

    @Override
    public Field<String> field2() {
        return InstanceDataCache.INSTANCE_DATA_CACHE.KEY_NAME;
    }

    @Override
    public Field<String> field3() {
        return InstanceDataCache.INSTANCE_DATA_CACHE.KEY_VALUE;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return InstanceDataCache.INSTANCE_DATA_CACHE.LAST_UPDATED;
    }

    @Override
    public String component1() {
        return getServerId();
    }

    @Override
    public String component2() {
        return getKeyName();
    }

    @Override
    public String component3() {
        return getKeyValue();
    }

    @Override
    public LocalDateTime component4() {
        return getLastUpdated();
    }

    @Override
    public String value1() {
        return getServerId();
    }

    @Override
    public String value2() {
        return getKeyName();
    }

    @Override
    public String value3() {
        return getKeyValue();
    }

    @Override
    public LocalDateTime value4() {
        return getLastUpdated();
    }

    @Override
    public InstanceDataCacheRecord value1(String value) {
        setServerId(value);
        return this;
    }

    @Override
    public InstanceDataCacheRecord value2(String value) {
        setKeyName(value);
        return this;
    }

    @Override
    public InstanceDataCacheRecord value3(String value) {
        setKeyValue(value);
        return this;
    }

    @Override
    public InstanceDataCacheRecord value4(LocalDateTime value) {
        setLastUpdated(value);
        return this;
    }

    @Override
    public InstanceDataCacheRecord values(String value1, String value2, String value3, LocalDateTime value4) {
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
     * Create a detached InstanceDataCacheRecord
     */
    public InstanceDataCacheRecord() {
        super(InstanceDataCache.INSTANCE_DATA_CACHE);
    }

    /**
     * Create a detached, initialised InstanceDataCacheRecord
     */
    public InstanceDataCacheRecord(String serverId, String keyName, String keyValue, LocalDateTime lastUpdated) {
        super(InstanceDataCache.INSTANCE_DATA_CACHE);

        setServerId(serverId);
        setKeyName(keyName);
        setKeyValue(keyValue);
        setLastUpdated(lastUpdated);
    }
}
