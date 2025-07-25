/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.gamedb.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import me.aa07.paradise.taskdaemon.database.gamedb.Indexes;
import me.aa07.paradise.taskdaemon.database.gamedb.Keys;
import me.aa07.paradise.taskdaemon.database.gamedb.ParadiseGamedb;
import me.aa07.paradise.taskdaemon.database.gamedb.tables.records.BanRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Ban extends TableImpl<BanRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>paradise_gamedb.ban</code>
     */
    public static final Ban BAN = new Ban();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BanRecord> getRecordType() {
        return BanRecord.class;
    }

    /**
     * The column <code>paradise_gamedb.ban.id</code>.
     */
    public final TableField<BanRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>paradise_gamedb.ban.bantime</code>.
     */
    public final TableField<BanRecord, LocalDateTime> BANTIME = createField(DSL.name("bantime"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.ban_round_id</code>.
     */
    public final TableField<BanRecord, Integer> BAN_ROUND_ID = createField(DSL.name("ban_round_id"), SQLDataType.INTEGER.defaultValue(DSL.inline("NULL", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.serverip</code>.
     */
    public final TableField<BanRecord, String> SERVERIP = createField(DSL.name("serverip"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.server_id</code>.
     */
    public final TableField<BanRecord, String> SERVER_ID = createField(DSL.name("server_id"), SQLDataType.VARCHAR(50).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.bantype</code>.
     */
    public final TableField<BanRecord, String> BANTYPE = createField(DSL.name("bantype"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.reason</code>.
     */
    public final TableField<BanRecord, String> REASON = createField(DSL.name("reason"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.job</code>.
     */
    public final TableField<BanRecord, String> JOB = createField(DSL.name("job"), SQLDataType.VARCHAR(32).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.duration</code>.
     */
    public final TableField<BanRecord, Integer> DURATION = createField(DSL.name("duration"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.rounds</code>.
     */
    public final TableField<BanRecord, Integer> ROUNDS = createField(DSL.name("rounds"), SQLDataType.INTEGER.defaultValue(DSL.inline("NULL", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.expiration_time</code>.
     */
    public final TableField<BanRecord, LocalDateTime> EXPIRATION_TIME = createField(DSL.name("expiration_time"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.ckey</code>.
     */
    public final TableField<BanRecord, String> CKEY = createField(DSL.name("ckey"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.computerid</code>.
     */
    public final TableField<BanRecord, String> COMPUTERID = createField(DSL.name("computerid"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.ip</code>.
     */
    public final TableField<BanRecord, String> IP = createField(DSL.name("ip"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.a_ckey</code>.
     */
    public final TableField<BanRecord, String> A_CKEY = createField(DSL.name("a_ckey"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.a_computerid</code>.
     */
    public final TableField<BanRecord, String> A_COMPUTERID = createField(DSL.name("a_computerid"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.a_ip</code>.
     */
    public final TableField<BanRecord, String> A_IP = createField(DSL.name("a_ip"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.who</code>.
     */
    public final TableField<BanRecord, String> WHO = createField(DSL.name("who"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.adminwho</code>.
     */
    public final TableField<BanRecord, String> ADMINWHO = createField(DSL.name("adminwho"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>paradise_gamedb.ban.edits</code>.
     */
    public final TableField<BanRecord, String> EDITS = createField(DSL.name("edits"), SQLDataType.CLOB.defaultValue(DSL.inline("NULL", SQLDataType.CLOB)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.unbanned</code>.
     */
    public final TableField<BanRecord, Byte> UNBANNED = createField(DSL.name("unbanned"), SQLDataType.TINYINT.defaultValue(DSL.inline("NULL", SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.unbanned_datetime</code>.
     */
    public final TableField<BanRecord, LocalDateTime> UNBANNED_DATETIME = createField(DSL.name("unbanned_datetime"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.inline("NULL", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.unbanned_round_id</code>.
     */
    public final TableField<BanRecord, Integer> UNBANNED_ROUND_ID = createField(DSL.name("unbanned_round_id"), SQLDataType.INTEGER.defaultValue(DSL.inline("NULL", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.unbanned_ckey</code>.
     */
    public final TableField<BanRecord, String> UNBANNED_CKEY = createField(DSL.name("unbanned_ckey"), SQLDataType.VARCHAR(32).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.unbanned_computerid</code>.
     */
    public final TableField<BanRecord, String> UNBANNED_COMPUTERID = createField(DSL.name("unbanned_computerid"), SQLDataType.VARCHAR(32).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.unbanned_ip</code>.
     */
    public final TableField<BanRecord, String> UNBANNED_IP = createField(DSL.name("unbanned_ip"), SQLDataType.VARCHAR(32).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>paradise_gamedb.ban.exportable</code>.
     */
    public final TableField<BanRecord, Byte> EXPORTABLE = createField(DSL.name("exportable"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("1", SQLDataType.TINYINT)), this, "");

    private Ban(Name alias, Table<BanRecord> aliased) {
        this(alias, aliased, null);
    }

    private Ban(Name alias, Table<BanRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_gamedb.ban</code> table reference
     */
    public Ban(String alias) {
        this(DSL.name(alias), BAN);
    }

    /**
     * Create an aliased <code>paradise_gamedb.ban</code> table reference
     */
    public Ban(Name alias) {
        this(alias, BAN);
    }

    /**
     * Create a <code>paradise_gamedb.ban</code> table reference
     */
    public Ban() {
        this(DSL.name("ban"), null);
    }

    public <O extends Record> Ban(Table<O> child, ForeignKey<O, BanRecord> key) {
        super(child, key, BAN);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseGamedb.PARADISE_GAMEDB;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.BAN_CKEY, Indexes.BAN_COMPUTERID, Indexes.BAN_EXPORTABLE, Indexes.BAN_IP);
    }

    @Override
    public Identity<BanRecord, Integer> getIdentity() {
        return (Identity<BanRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<BanRecord> getPrimaryKey() {
        return Keys.KEY_BAN_PRIMARY;
    }

    @Override
    public Ban as(String alias) {
        return new Ban(DSL.name(alias), this);
    }

    @Override
    public Ban as(Name alias) {
        return new Ban(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Ban rename(String name) {
        return new Ban(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Ban rename(Name name) {
        return new Ban(name, null);
    }
}
