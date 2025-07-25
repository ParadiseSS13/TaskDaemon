/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.ConvertAppSessionsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ConvertAppSessions extends TableImpl<ConvertAppSessionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>paradise_forums.convert_app_sessions</code>
     */
    public static final ConvertAppSessions CONVERT_APP_SESSIONS = new ConvertAppSessions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ConvertAppSessionsRecord> getRecordType() {
        return ConvertAppSessionsRecord.class;
    }

    /**
     * The column
     * <code>paradise_forums.convert_app_sessions.session_app_id</code>. ID
     * Number
     */
    public final TableField<ConvertAppSessionsRecord, ULong> SESSION_APP_ID = createField(DSL.name("session_app_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINTUNSIGNED)), this, "ID Number");

    /**
     * The column
     * <code>paradise_forums.convert_app_sessions.session_app_data</code>.
     * Session Data containing rows converted, completed steps, etc.
     */
    public final TableField<ConvertAppSessionsRecord, String> SESSION_APP_DATA = createField(DSL.name("session_app_data"), SQLDataType.CLOB.defaultValue(DSL.inline("NULL", SQLDataType.CLOB)), this, "Session Data containing rows converted, completed steps, etc.");

    private ConvertAppSessions(Name alias, Table<ConvertAppSessionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private ConvertAppSessions(Name alias, Table<ConvertAppSessionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.convert_app_sessions</code> table
     * reference
     */
    public ConvertAppSessions(String alias) {
        this(DSL.name(alias), CONVERT_APP_SESSIONS);
    }

    /**
     * Create an aliased <code>paradise_forums.convert_app_sessions</code> table
     * reference
     */
    public ConvertAppSessions(Name alias) {
        this(alias, CONVERT_APP_SESSIONS);
    }

    /**
     * Create a <code>paradise_forums.convert_app_sessions</code> table
     * reference
     */
    public ConvertAppSessions() {
        this(DSL.name("convert_app_sessions"), null);
    }

    public <O extends Record> ConvertAppSessions(Table<O> child, ForeignKey<O, ConvertAppSessionsRecord> key) {
        super(child, key, CONVERT_APP_SESSIONS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public UniqueKey<ConvertAppSessionsRecord> getPrimaryKey() {
        return Keys.KEY_CONVERT_APP_SESSIONS_PRIMARY;
    }

    @Override
    public ConvertAppSessions as(String alias) {
        return new ConvertAppSessions(DSL.name(alias), this);
    }

    @Override
    public ConvertAppSessions as(Name alias) {
        return new ConvertAppSessions(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ConvertAppSessions rename(String name) {
        return new ConvertAppSessions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ConvertAppSessions rename(Name name) {
        return new ConvertAppSessions(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<ULong, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
