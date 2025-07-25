/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables.records;


import me.aa07.paradise.taskdaemon.database.forums.tables.CoreGroupPromotions;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreGroupPromotionsRecord extends UpdatableRecordImpl<CoreGroupPromotionsRecord> implements Record5<ULong, ULong, Byte, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_forums.core_group_promotions.promote_id</code>.
     * ID Number
     */
    public void setPromoteId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_forums.core_group_promotions.promote_id</code>.
     * ID Number
     */
    public ULong getPromoteId() {
        return (ULong) get(0);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_group_promotions.promote_position</code>.
     * Position of promotion rule
     */
    public void setPromotePosition(ULong value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_group_promotions.promote_position</code>.
     * Position of promotion rule
     */
    public ULong getPromotePosition() {
        return (ULong) get(1);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_group_promotions.promote_enabled</code>.
     * Whether the rule is enabled or not
     */
    public void setPromoteEnabled(Byte value) {
        set(2, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_group_promotions.promote_enabled</code>.
     * Whether the rule is enabled or not
     */
    public Byte getPromoteEnabled() {
        return (Byte) get(2);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_group_promotions.promote_filters</code>.
     * Json-encoded array of filters that a member must meet in order for this
     * rule to apply
     */
    public void setPromoteFilters(String value) {
        set(3, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_group_promotions.promote_filters</code>.
     * Json-encoded array of filters that a member must meet in order for this
     * rule to apply
     */
    public String getPromoteFilters() {
        return (String) get(3);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_group_promotions.promote_actions</code>.
     * Json-encoded array of actions taken when this rule applies
     */
    public void setPromoteActions(String value) {
        set(4, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_group_promotions.promote_actions</code>.
     * Json-encoded array of actions taken when this rule applies
     */
    public String getPromoteActions() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<ULong, ULong, Byte, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<ULong, ULong, Byte, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return CoreGroupPromotions.CORE_GROUP_PROMOTIONS.PROMOTE_ID;
    }

    @Override
    public Field<ULong> field2() {
        return CoreGroupPromotions.CORE_GROUP_PROMOTIONS.PROMOTE_POSITION;
    }

    @Override
    public Field<Byte> field3() {
        return CoreGroupPromotions.CORE_GROUP_PROMOTIONS.PROMOTE_ENABLED;
    }

    @Override
    public Field<String> field4() {
        return CoreGroupPromotions.CORE_GROUP_PROMOTIONS.PROMOTE_FILTERS;
    }

    @Override
    public Field<String> field5() {
        return CoreGroupPromotions.CORE_GROUP_PROMOTIONS.PROMOTE_ACTIONS;
    }

    @Override
    public ULong component1() {
        return getPromoteId();
    }

    @Override
    public ULong component2() {
        return getPromotePosition();
    }

    @Override
    public Byte component3() {
        return getPromoteEnabled();
    }

    @Override
    public String component4() {
        return getPromoteFilters();
    }

    @Override
    public String component5() {
        return getPromoteActions();
    }

    @Override
    public ULong value1() {
        return getPromoteId();
    }

    @Override
    public ULong value2() {
        return getPromotePosition();
    }

    @Override
    public Byte value3() {
        return getPromoteEnabled();
    }

    @Override
    public String value4() {
        return getPromoteFilters();
    }

    @Override
    public String value5() {
        return getPromoteActions();
    }

    @Override
    public CoreGroupPromotionsRecord value1(ULong value) {
        setPromoteId(value);
        return this;
    }

    @Override
    public CoreGroupPromotionsRecord value2(ULong value) {
        setPromotePosition(value);
        return this;
    }

    @Override
    public CoreGroupPromotionsRecord value3(Byte value) {
        setPromoteEnabled(value);
        return this;
    }

    @Override
    public CoreGroupPromotionsRecord value4(String value) {
        setPromoteFilters(value);
        return this;
    }

    @Override
    public CoreGroupPromotionsRecord value5(String value) {
        setPromoteActions(value);
        return this;
    }

    @Override
    public CoreGroupPromotionsRecord values(ULong value1, ULong value2, Byte value3, String value4, String value5) {
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
     * Create a detached CoreGroupPromotionsRecord
     */
    public CoreGroupPromotionsRecord() {
        super(CoreGroupPromotions.CORE_GROUP_PROMOTIONS);
    }

    /**
     * Create a detached, initialised CoreGroupPromotionsRecord
     */
    public CoreGroupPromotionsRecord(ULong promoteId, ULong promotePosition, Byte promoteEnabled, String promoteFilters, String promoteActions) {
        super(CoreGroupPromotions.CORE_GROUP_PROMOTIONS);

        setPromoteId(promoteId);
        setPromotePosition(promotePosition);
        setPromoteEnabled(promoteEnabled);
        setPromoteFilters(promoteFilters);
        setPromoteActions(promoteActions);
    }
}
