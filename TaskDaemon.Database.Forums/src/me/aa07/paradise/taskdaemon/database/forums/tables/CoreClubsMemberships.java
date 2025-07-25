/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import java.util.Arrays;
import java.util.List;

import me.aa07.paradise.taskdaemon.database.forums.Indexes;
import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.enums.CoreClubsMembershipsStatus;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.CoreClubsMembershipsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreClubsMemberships extends TableImpl<CoreClubsMembershipsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>paradise_forums.core_clubs_memberships</code>
     */
    public static final CoreClubsMemberships CORE_CLUBS_MEMBERSHIPS = new CoreClubsMemberships();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoreClubsMembershipsRecord> getRecordType() {
        return CoreClubsMembershipsRecord.class;
    }

    /**
     * The column <code>paradise_forums.core_clubs_memberships.club_id</code>.
     * Club ID Number
     */
    public final TableField<CoreClubsMembershipsRecord, ULong> CLUB_ID = createField(DSL.name("club_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Club ID Number");

    /**
     * The column <code>paradise_forums.core_clubs_memberships.member_id</code>.
     * Member ID number
     */
    public final TableField<CoreClubsMembershipsRecord, ULong> MEMBER_ID = createField(DSL.name("member_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Member ID number");

    /**
     * The column <code>paradise_forums.core_clubs_memberships.joined</code>.
     * Unix timestamp of when the member joined the club
     */
    public final TableField<CoreClubsMembershipsRecord, UInteger> JOINED = createField(DSL.name("joined"), SQLDataType.INTEGERUNSIGNED.nullable(false), this, "Unix timestamp of when the member joined the club");

    /**
     * The column <code>paradise_forums.core_clubs_memberships.added_by</code>.
     * If added by a leader, the leader's member ID. NULL if joined themselves.
     */
    public final TableField<CoreClubsMembershipsRecord, ULong> ADDED_BY = createField(DSL.name("added_by"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.inline("NULL", SQLDataType.BIGINTUNSIGNED)), this, "If added by a leader, the leader's member ID. NULL if joined themselves.");

    /**
     * The column
     * <code>paradise_forums.core_clubs_memberships.invited_by</code>. If
     * invited by another member, the member id. NULL if joined themselves.
     */
    public final TableField<CoreClubsMembershipsRecord, ULong> INVITED_BY = createField(DSL.name("invited_by"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.inline("NULL", SQLDataType.BIGINTUNSIGNED)), this, "If invited by another member, the member id. NULL if joined themselves.");

    /**
     * The column <code>paradise_forums.core_clubs_memberships.status</code>.
     * The status of the membership. member is normal member, requested/invited
     * are in the process of joining, leader is leader, declined means their
     * request to join was declined, banned is banned by a leader
     */
    public final TableField<CoreClubsMembershipsRecord, CoreClubsMembershipsStatus> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR(25).defaultValue(DSL.inline("'member'", SQLDataType.VARCHAR)).asEnumDataType(me.aa07.paradise.taskdaemon.database.forums.enums.CoreClubsMembershipsStatus.class), this, "The status of the membership. member is normal member, requested/invited are in the process of joining, leader is leader, declined means their request to join was declined, banned is banned by a leader");

    /**
     * The column
     * <code>paradise_forums.core_clubs_memberships.rules_acknowledged</code>.
     */
    public final TableField<CoreClubsMembershipsRecord, Boolean> RULES_ACKNOWLEDGED = createField(DSL.name("rules_acknowledged"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    private CoreClubsMemberships(Name alias, Table<CoreClubsMembershipsRecord> aliased) {
        this(alias, aliased, null);
    }

    private CoreClubsMemberships(Name alias, Table<CoreClubsMembershipsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.core_clubs_memberships</code>
     * table reference
     */
    public CoreClubsMemberships(String alias) {
        this(DSL.name(alias), CORE_CLUBS_MEMBERSHIPS);
    }

    /**
     * Create an aliased <code>paradise_forums.core_clubs_memberships</code>
     * table reference
     */
    public CoreClubsMemberships(Name alias) {
        this(alias, CORE_CLUBS_MEMBERSHIPS);
    }

    /**
     * Create a <code>paradise_forums.core_clubs_memberships</code> table
     * reference
     */
    public CoreClubsMemberships() {
        this(DSL.name("core_clubs_memberships"), null);
    }

    public <O extends Record> CoreClubsMemberships(Table<O> child, ForeignKey<O, CoreClubsMembershipsRecord> key) {
        super(child, key, CORE_CLUBS_MEMBERSHIPS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.CORE_CLUBS_MEMBERSHIPS_MEMBER_ID);
    }

    @Override
    public UniqueKey<CoreClubsMembershipsRecord> getPrimaryKey() {
        return Keys.KEY_CORE_CLUBS_MEMBERSHIPS_PRIMARY;
    }

    @Override
    public CoreClubsMemberships as(String alias) {
        return new CoreClubsMemberships(DSL.name(alias), this);
    }

    @Override
    public CoreClubsMemberships as(Name alias) {
        return new CoreClubsMemberships(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreClubsMemberships rename(String name) {
        return new CoreClubsMemberships(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreClubsMemberships rename(Name name) {
        return new CoreClubsMemberships(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<ULong, ULong, UInteger, ULong, ULong, CoreClubsMembershipsStatus, Boolean> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
