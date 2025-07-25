/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables.records;


import me.aa07.paradise.taskdaemon.database.forums.enums.CoreModeratorsType;
import me.aa07.paradise.taskdaemon.database.forums.tables.CoreModerators;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UByte;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreModeratorsRecord extends UpdatableRecordImpl<CoreModeratorsRecord> implements Record5<CoreModeratorsType, Long, String, Integer, UByte> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_forums.core_moderators.type</code>. Member or
     * group
     */
    public void setType(CoreModeratorsType value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_forums.core_moderators.type</code>. Member or
     * group
     */
    public CoreModeratorsType getType() {
        return (CoreModeratorsType) get(0);
    }

    /**
     * Setter for <code>paradise_forums.core_moderators.id</code>. ID Number
     */
    public void setId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>paradise_forums.core_moderators.id</code>. ID Number
     */
    public Long getId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>paradise_forums.core_moderators.perms</code>.
     * Permissions
     */
    public void setPerms(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>paradise_forums.core_moderators.perms</code>.
     * Permissions
     */
    public String getPerms() {
        return (String) get(2);
    }

    /**
     * Setter for <code>paradise_forums.core_moderators.updated</code>. Updated
     */
    public void setUpdated(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>paradise_forums.core_moderators.updated</code>. Updated
     */
    public Integer getUpdated() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>paradise_forums.core_moderators.show_badge</code>. Show
     * a badge?
     */
    public void setShowBadge(UByte value) {
        set(4, value);
    }

    /**
     * Getter for <code>paradise_forums.core_moderators.show_badge</code>. Show
     * a badge?
     */
    public UByte getShowBadge() {
        return (UByte) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<CoreModeratorsType, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<CoreModeratorsType, Long, String, Integer, UByte> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<CoreModeratorsType, Long, String, Integer, UByte> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<CoreModeratorsType> field1() {
        return CoreModerators.CORE_MODERATORS.TYPE;
    }

    @Override
    public Field<Long> field2() {
        return CoreModerators.CORE_MODERATORS.ID;
    }

    @Override
    public Field<String> field3() {
        return CoreModerators.CORE_MODERATORS.PERMS;
    }

    @Override
    public Field<Integer> field4() {
        return CoreModerators.CORE_MODERATORS.UPDATED;
    }

    @Override
    public Field<UByte> field5() {
        return CoreModerators.CORE_MODERATORS.SHOW_BADGE;
    }

    @Override
    public CoreModeratorsType component1() {
        return getType();
    }

    @Override
    public Long component2() {
        return getId();
    }

    @Override
    public String component3() {
        return getPerms();
    }

    @Override
    public Integer component4() {
        return getUpdated();
    }

    @Override
    public UByte component5() {
        return getShowBadge();
    }

    @Override
    public CoreModeratorsType value1() {
        return getType();
    }

    @Override
    public Long value2() {
        return getId();
    }

    @Override
    public String value3() {
        return getPerms();
    }

    @Override
    public Integer value4() {
        return getUpdated();
    }

    @Override
    public UByte value5() {
        return getShowBadge();
    }

    @Override
    public CoreModeratorsRecord value1(CoreModeratorsType value) {
        setType(value);
        return this;
    }

    @Override
    public CoreModeratorsRecord value2(Long value) {
        setId(value);
        return this;
    }

    @Override
    public CoreModeratorsRecord value3(String value) {
        setPerms(value);
        return this;
    }

    @Override
    public CoreModeratorsRecord value4(Integer value) {
        setUpdated(value);
        return this;
    }

    @Override
    public CoreModeratorsRecord value5(UByte value) {
        setShowBadge(value);
        return this;
    }

    @Override
    public CoreModeratorsRecord values(CoreModeratorsType value1, Long value2, String value3, Integer value4, UByte value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoreModeratorsRecord
     */
    public CoreModeratorsRecord() {
        super(CoreModerators.CORE_MODERATORS);
    }

    /**
     * Create a detached, initialised CoreModeratorsRecord
     */
    public CoreModeratorsRecord(CoreModeratorsType type, Long id, String perms, Integer updated, UByte showBadge) {
        super(CoreModerators.CORE_MODERATORS);

        setType(type);
        setId(id);
        setPerms(perms);
        setUpdated(updated);
        setShowBadge(showBadge);
    }
}
