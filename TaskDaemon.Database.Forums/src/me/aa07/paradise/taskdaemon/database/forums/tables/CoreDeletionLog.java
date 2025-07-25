/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import java.util.Arrays;
import java.util.List;

import me.aa07.paradise.taskdaemon.database.forums.Indexes;
import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.CoreDeletionLogRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row12;
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
public class CoreDeletionLog extends TableImpl<CoreDeletionLogRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>paradise_forums.core_deletion_log</code>
     */
    public static final CoreDeletionLog CORE_DELETION_LOG = new CoreDeletionLog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoreDeletionLogRecord> getRecordType() {
        return CoreDeletionLogRecord.class;
    }

    /**
     * The column <code>paradise_forums.core_deletion_log.dellog_id</code>. ID
     * Number
     */
    public final TableField<CoreDeletionLogRecord, ULong> DELLOG_ID = createField(DSL.name("dellog_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "ID Number");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_content_class</code>.
     */
    public final TableField<CoreDeletionLogRecord, String> DELLOG_CONTENT_CLASS = createField(DSL.name("dellog_content_class"), SQLDataType.VARCHAR(255).nullable(false).defaultValue(DSL.inline("''", SQLDataType.VARCHAR)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_content_id</code>.
     */
    public final TableField<CoreDeletionLogRecord, Long> DELLOG_CONTENT_ID = createField(DSL.name("dellog_content_id"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINT)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_content_title</code>.
     */
    public final TableField<CoreDeletionLogRecord, String> DELLOG_CONTENT_TITLE = createField(DSL.name("dellog_content_title"), SQLDataType.VARCHAR(255).nullable(false).defaultValue(DSL.inline("''", SQLDataType.VARCHAR)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_content_seo_title</code>.
     */
    public final TableField<CoreDeletionLogRecord, String> DELLOG_CONTENT_SEO_TITLE = createField(DSL.name("dellog_content_seo_title"), SQLDataType.VARCHAR(255).nullable(false).defaultValue(DSL.inline("''", SQLDataType.VARCHAR)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_deleted_by</code>.
     */
    public final TableField<CoreDeletionLogRecord, Long> DELLOG_DELETED_BY = createField(DSL.name("dellog_deleted_by"), SQLDataType.BIGINT.defaultValue(DSL.inline("NULL", SQLDataType.BIGINT)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_deleted_by_name</code>.
     */
    public final TableField<CoreDeletionLogRecord, String> DELLOG_DELETED_BY_NAME = createField(DSL.name("dellog_deleted_by_name"), SQLDataType.VARCHAR(255).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_deleted_date</code>.
     */
    public final TableField<CoreDeletionLogRecord, Long> DELLOG_DELETED_DATE = createField(DSL.name("dellog_deleted_date"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINT)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_deleted_by_seo_name</code>.
     */
    public final TableField<CoreDeletionLogRecord, String> DELLOG_DELETED_BY_SEO_NAME = createField(DSL.name("dellog_deleted_by_seo_name"), SQLDataType.VARCHAR(255).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_content_permissions</code>.
     */
    public final TableField<CoreDeletionLogRecord, String> DELLOG_CONTENT_PERMISSIONS = createField(DSL.name("dellog_content_permissions"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_content_container_id</code>.
     */
    public final TableField<CoreDeletionLogRecord, Long> DELLOG_CONTENT_CONTAINER_ID = createField(DSL.name("dellog_content_container_id"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINT)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_deletion_log.dellog_content_container_class</code>.
     */
    public final TableField<CoreDeletionLogRecord, String> DELLOG_CONTENT_CONTAINER_CLASS = createField(DSL.name("dellog_content_container_class"), SQLDataType.VARCHAR(255).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    private CoreDeletionLog(Name alias, Table<CoreDeletionLogRecord> aliased) {
        this(alias, aliased, null);
    }

    private CoreDeletionLog(Name alias, Table<CoreDeletionLogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.core_deletion_log</code> table
     * reference
     */
    public CoreDeletionLog(String alias) {
        this(DSL.name(alias), CORE_DELETION_LOG);
    }

    /**
     * Create an aliased <code>paradise_forums.core_deletion_log</code> table
     * reference
     */
    public CoreDeletionLog(Name alias) {
        this(alias, CORE_DELETION_LOG);
    }

    /**
     * Create a <code>paradise_forums.core_deletion_log</code> table reference
     */
    public CoreDeletionLog() {
        this(DSL.name("core_deletion_log"), null);
    }

    public <O extends Record> CoreDeletionLog(Table<O> child, ForeignKey<O, CoreDeletionLogRecord> key) {
        super(child, key, CORE_DELETION_LOG);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.CORE_DELETION_LOG_DELLOG_CONTENT, Indexes.CORE_DELETION_LOG_DELLOG_DELETED_BY, Indexes.CORE_DELETION_LOG_DELLOG_DELETED_DATE);
    }

    @Override
    public Identity<CoreDeletionLogRecord, ULong> getIdentity() {
        return (Identity<CoreDeletionLogRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<CoreDeletionLogRecord> getPrimaryKey() {
        return Keys.KEY_CORE_DELETION_LOG_PRIMARY;
    }

    @Override
    public CoreDeletionLog as(String alias) {
        return new CoreDeletionLog(DSL.name(alias), this);
    }

    @Override
    public CoreDeletionLog as(Name alias) {
        return new CoreDeletionLog(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreDeletionLog rename(String name) {
        return new CoreDeletionLog(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreDeletionLog rename(Name name) {
        return new CoreDeletionLog(name, null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row12<ULong, String, Long, String, String, Long, String, Long, String, String, Long, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }
}
