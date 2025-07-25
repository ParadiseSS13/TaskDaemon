/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables.records;


import me.aa07.paradise.taskdaemon.database.forums.tables.CoreSysSocialGroups;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreSysSocialGroupsRecord extends UpdatableRecordImpl<CoreSysSocialGroupsRecord> implements Record2<ULong, ULong> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_forums.core_sys_social_groups.group_id</code>.
     * ID Number
     */
    public void setGroupId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_forums.core_sys_social_groups.group_id</code>.
     * ID Number
     */
    public ULong getGroupId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>paradise_forums.core_sys_social_groups.owner_id</code>.
     */
    public void setOwnerId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>paradise_forums.core_sys_social_groups.owner_id</code>.
     */
    public ULong getOwnerId() {
        return (ULong) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<ULong, ULong> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<ULong, ULong> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return CoreSysSocialGroups.CORE_SYS_SOCIAL_GROUPS.GROUP_ID;
    }

    @Override
    public Field<ULong> field2() {
        return CoreSysSocialGroups.CORE_SYS_SOCIAL_GROUPS.OWNER_ID;
    }

    @Override
    public ULong component1() {
        return getGroupId();
    }

    @Override
    public ULong component2() {
        return getOwnerId();
    }

    @Override
    public ULong value1() {
        return getGroupId();
    }

    @Override
    public ULong value2() {
        return getOwnerId();
    }

    @Override
    public CoreSysSocialGroupsRecord value1(ULong value) {
        setGroupId(value);
        return this;
    }

    @Override
    public CoreSysSocialGroupsRecord value2(ULong value) {
        setOwnerId(value);
        return this;
    }

    @Override
    public CoreSysSocialGroupsRecord values(ULong value1, ULong value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoreSysSocialGroupsRecord
     */
    public CoreSysSocialGroupsRecord() {
        super(CoreSysSocialGroups.CORE_SYS_SOCIAL_GROUPS);
    }

    /**
     * Create a detached, initialised CoreSysSocialGroupsRecord
     */
    public CoreSysSocialGroupsRecord(ULong groupId, ULong ownerId) {
        super(CoreSysSocialGroups.CORE_SYS_SOCIAL_GROUPS);

        setGroupId(groupId);
        setOwnerId(ownerId);
    }
}
