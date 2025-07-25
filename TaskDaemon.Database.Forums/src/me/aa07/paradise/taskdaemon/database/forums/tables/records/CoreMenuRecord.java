/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables.records;


import me.aa07.paradise.taskdaemon.database.forums.tables.CoreMenu;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreMenuRecord extends UpdatableRecordImpl<CoreMenuRecord> implements Record8<ULong, String, String, String, UInteger, UInteger, String, UByte> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_forums.core_menu.id</code>. ID Number
     */
    public void setId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_forums.core_menu.id</code>. ID Number
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>paradise_forums.core_menu.app</code>. The application
     * key for the item
     */
    public void setApp(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>paradise_forums.core_menu.app</code>. The application
     * key for the item
     */
    public String getApp() {
        return (String) get(1);
    }

    /**
     * Setter for <code>paradise_forums.core_menu.extension</code>. The
     * FrontNavigation extension key
     */
    public void setExtension(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>paradise_forums.core_menu.extension</code>. The
     * FrontNavigation extension key
     */
    public String getExtension() {
        return (String) get(2);
    }

    /**
     * Setter for <code>paradise_forums.core_menu.config</code>. Any additional
     * configuration
     */
    public void setConfig(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>paradise_forums.core_menu.config</code>. Any additional
     * configuration
     */
    public String getConfig() {
        return (String) get(3);
    }

    /**
     * Setter for <code>paradise_forums.core_menu.position</code>. This item's
     * position within the bar
     */
    public void setPosition(UInteger value) {
        set(4, value);
    }

    /**
     * Getter for <code>paradise_forums.core_menu.position</code>. This item's
     * position within the bar
     */
    public UInteger getPosition() {
        return (UInteger) get(4);
    }

    /**
     * Setter for <code>paradise_forums.core_menu.parent</code>. The ID of the
     * parent item if this is on the second bar, or NULL if the first bar
     */
    public void setParent(UInteger value) {
        set(5, value);
    }

    /**
     * Getter for <code>paradise_forums.core_menu.parent</code>. The ID of the
     * parent item if this is on the second bar, or NULL if the first bar
     */
    public UInteger getParent() {
        return (UInteger) get(5);
    }

    /**
     * Setter for <code>paradise_forums.core_menu.permissions</code>.
     * Comma-delimited list of group IDs, or * for all, NULL to inherit
     */
    public void setPermissions(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>paradise_forums.core_menu.permissions</code>.
     * Comma-delimited list of group IDs, or * for all, NULL to inherit
     */
    public String getPermissions() {
        return (String) get(6);
    }

    /**
     * Setter for <code>paradise_forums.core_menu.is_menu_child</code>. If this
     * item is a child of a dropdown menu
     */
    public void setIsMenuChild(UByte value) {
        set(7, value);
    }

    /**
     * Getter for <code>paradise_forums.core_menu.is_menu_child</code>. If this
     * item is a child of a dropdown menu
     */
    public UByte getIsMenuChild() {
        return (UByte) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<ULong, String, String, String, UInteger, UInteger, String, UByte> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<ULong, String, String, String, UInteger, UInteger, String, UByte> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return CoreMenu.CORE_MENU.ID;
    }

    @Override
    public Field<String> field2() {
        return CoreMenu.CORE_MENU.APP;
    }

    @Override
    public Field<String> field3() {
        return CoreMenu.CORE_MENU.EXTENSION;
    }

    @Override
    public Field<String> field4() {
        return CoreMenu.CORE_MENU.CONFIG;
    }

    @Override
    public Field<UInteger> field5() {
        return CoreMenu.CORE_MENU.POSITION;
    }

    @Override
    public Field<UInteger> field6() {
        return CoreMenu.CORE_MENU.PARENT;
    }

    @Override
    public Field<String> field7() {
        return CoreMenu.CORE_MENU.PERMISSIONS;
    }

    @Override
    public Field<UByte> field8() {
        return CoreMenu.CORE_MENU.IS_MENU_CHILD;
    }

    @Override
    public ULong component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getApp();
    }

    @Override
    public String component3() {
        return getExtension();
    }

    @Override
    public String component4() {
        return getConfig();
    }

    @Override
    public UInteger component5() {
        return getPosition();
    }

    @Override
    public UInteger component6() {
        return getParent();
    }

    @Override
    public String component7() {
        return getPermissions();
    }

    @Override
    public UByte component8() {
        return getIsMenuChild();
    }

    @Override
    public ULong value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getApp();
    }

    @Override
    public String value3() {
        return getExtension();
    }

    @Override
    public String value4() {
        return getConfig();
    }

    @Override
    public UInteger value5() {
        return getPosition();
    }

    @Override
    public UInteger value6() {
        return getParent();
    }

    @Override
    public String value7() {
        return getPermissions();
    }

    @Override
    public UByte value8() {
        return getIsMenuChild();
    }

    @Override
    public CoreMenuRecord value1(ULong value) {
        setId(value);
        return this;
    }

    @Override
    public CoreMenuRecord value2(String value) {
        setApp(value);
        return this;
    }

    @Override
    public CoreMenuRecord value3(String value) {
        setExtension(value);
        return this;
    }

    @Override
    public CoreMenuRecord value4(String value) {
        setConfig(value);
        return this;
    }

    @Override
    public CoreMenuRecord value5(UInteger value) {
        setPosition(value);
        return this;
    }

    @Override
    public CoreMenuRecord value6(UInteger value) {
        setParent(value);
        return this;
    }

    @Override
    public CoreMenuRecord value7(String value) {
        setPermissions(value);
        return this;
    }

    @Override
    public CoreMenuRecord value8(UByte value) {
        setIsMenuChild(value);
        return this;
    }

    @Override
    public CoreMenuRecord values(ULong value1, String value2, String value3, String value4, UInteger value5, UInteger value6, String value7, UByte value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoreMenuRecord
     */
    public CoreMenuRecord() {
        super(CoreMenu.CORE_MENU);
    }

    /**
     * Create a detached, initialised CoreMenuRecord
     */
    public CoreMenuRecord(ULong id, String app, String extension, String config, UInteger position, UInteger parent, String permissions, UByte isMenuChild) {
        super(CoreMenu.CORE_MENU);

        setId(id);
        setApp(app);
        setExtension(extension);
        setConfig(config);
        setPosition(position);
        setParent(parent);
        setPermissions(permissions);
        setIsMenuChild(isMenuChild);
    }
}
