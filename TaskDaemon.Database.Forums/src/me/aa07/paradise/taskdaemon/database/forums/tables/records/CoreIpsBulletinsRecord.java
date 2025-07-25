/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables.records;


import me.aa07.paradise.taskdaemon.database.forums.tables.CoreIpsBulletins;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreIpsBulletinsRecord extends UpdatableRecordImpl<CoreIpsBulletinsRecord> implements Record11<ULong, String, String, String, String, String, String, UInteger, String, UInteger, UInteger> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.id</code>. ID Number
     */
    public void setId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.id</code>. ID Number
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.title</code>. The
     * bulletin title
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.title</code>. The
     * bulletin title
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.body</code>. The
     * bulletin body
     */
    public void setBody(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.body</code>. The
     * bulletin body
     */
    public String getBody() {
        return (String) get(2);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.severity</code>. The
     * bulletin severity
     */
    public void setSeverity(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.severity</code>. The
     * bulletin severity
     */
    public String getSeverity() {
        return (String) get(3);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.style</code>. The
     * bulletin style
     */
    public void setStyle(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.style</code>. The
     * bulletin style
     */
    public String getStyle() {
        return (String) get(4);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.dismissible</code>.
     * If the bulletin can be dismissed
     */
    public void setDismissible(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.dismissible</code>.
     * If the bulletin can be dismissed
     */
    public String getDismissible() {
        return (String) get(5);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.link</code>. Where
     * the notification should link to
     */
    public void setLink(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.link</code>. Where
     * the notification should link to
     */
    public String getLink() {
        return (String) get(6);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.cached</code>. Unix
     * timestamp of when this row was cached
     */
    public void setCached(UInteger value) {
        set(7, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.cached</code>. Unix
     * timestamp of when this row was cached
     */
    public UInteger getCached() {
        return (UInteger) get(7);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.conditions</code>.
     * PHP code to check if notification should show
     */
    public void setConditions(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.conditions</code>.
     * PHP code to check if notification should show
     */
    public String getConditions() {
        return (String) get(8);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.min_version</code>.
     */
    public void setMinVersion(UInteger value) {
        set(9, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.min_version</code>.
     */
    public UInteger getMinVersion() {
        return (UInteger) get(9);
    }

    /**
     * Setter for <code>paradise_forums.core_ips_bulletins.max_version</code>.
     */
    public void setMaxVersion(UInteger value) {
        set(10, value);
    }

    /**
     * Getter for <code>paradise_forums.core_ips_bulletins.max_version</code>.
     */
    public UInteger getMaxVersion() {
        return (UInteger) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<ULong, String, String, String, String, String, String, UInteger, String, UInteger, UInteger> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<ULong, String, String, String, String, String, String, UInteger, String, UInteger, UInteger> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.ID;
    }

    @Override
    public Field<String> field2() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.TITLE;
    }

    @Override
    public Field<String> field3() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.BODY;
    }

    @Override
    public Field<String> field4() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.SEVERITY;
    }

    @Override
    public Field<String> field5() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.STYLE;
    }

    @Override
    public Field<String> field6() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.DISMISSIBLE;
    }

    @Override
    public Field<String> field7() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.LINK;
    }

    @Override
    public Field<UInteger> field8() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.CACHED;
    }

    @Override
    public Field<String> field9() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.CONDITIONS;
    }

    @Override
    public Field<UInteger> field10() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.MIN_VERSION;
    }

    @Override
    public Field<UInteger> field11() {
        return CoreIpsBulletins.CORE_IPS_BULLETINS.MAX_VERSION;
    }

    @Override
    public ULong component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getBody();
    }

    @Override
    public String component4() {
        return getSeverity();
    }

    @Override
    public String component5() {
        return getStyle();
    }

    @Override
    public String component6() {
        return getDismissible();
    }

    @Override
    public String component7() {
        return getLink();
    }

    @Override
    public UInteger component8() {
        return getCached();
    }

    @Override
    public String component9() {
        return getConditions();
    }

    @Override
    public UInteger component10() {
        return getMinVersion();
    }

    @Override
    public UInteger component11() {
        return getMaxVersion();
    }

    @Override
    public ULong value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getBody();
    }

    @Override
    public String value4() {
        return getSeverity();
    }

    @Override
    public String value5() {
        return getStyle();
    }

    @Override
    public String value6() {
        return getDismissible();
    }

    @Override
    public String value7() {
        return getLink();
    }

    @Override
    public UInteger value8() {
        return getCached();
    }

    @Override
    public String value9() {
        return getConditions();
    }

    @Override
    public UInteger value10() {
        return getMinVersion();
    }

    @Override
    public UInteger value11() {
        return getMaxVersion();
    }

    @Override
    public CoreIpsBulletinsRecord value1(ULong value) {
        setId(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value3(String value) {
        setBody(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value4(String value) {
        setSeverity(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value5(String value) {
        setStyle(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value6(String value) {
        setDismissible(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value7(String value) {
        setLink(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value8(UInteger value) {
        setCached(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value9(String value) {
        setConditions(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value10(UInteger value) {
        setMinVersion(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord value11(UInteger value) {
        setMaxVersion(value);
        return this;
    }

    @Override
    public CoreIpsBulletinsRecord values(ULong value1, String value2, String value3, String value4, String value5, String value6, String value7, UInteger value8, String value9, UInteger value10, UInteger value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoreIpsBulletinsRecord
     */
    public CoreIpsBulletinsRecord() {
        super(CoreIpsBulletins.CORE_IPS_BULLETINS);
    }

    /**
     * Create a detached, initialised CoreIpsBulletinsRecord
     */
    public CoreIpsBulletinsRecord(ULong id, String title, String body, String severity, String style, String dismissible, String link, UInteger cached, String conditions, UInteger minVersion, UInteger maxVersion) {
        super(CoreIpsBulletins.CORE_IPS_BULLETINS);

        setId(id);
        setTitle(title);
        setBody(body);
        setSeverity(severity);
        setStyle(style);
        setDismissible(dismissible);
        setLink(link);
        setCached(cached);
        setConditions(conditions);
        setMinVersion(minVersion);
        setMaxVersion(maxVersion);
    }
}
