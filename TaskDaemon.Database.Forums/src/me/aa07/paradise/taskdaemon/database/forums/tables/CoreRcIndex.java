/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import java.util.Arrays;
import java.util.List;

import me.aa07.paradise.taskdaemon.database.forums.Indexes;
import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.enums.CoreRcIndexStatus;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.CoreRcIndexRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row14;
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
public class CoreRcIndex extends TableImpl<CoreRcIndexRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>paradise_forums.core_rc_index</code>
     */
    public static final CoreRcIndex CORE_RC_INDEX = new CoreRcIndex();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoreRcIndexRecord> getRecordType() {
        return CoreRcIndexRecord.class;
    }

    /**
     * The column <code>paradise_forums.core_rc_index.id</code>. ID Number
     */
    public final TableField<CoreRcIndexRecord, ULong> ID = createField(DSL.name("id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "ID Number");

    /**
     * The column <code>paradise_forums.core_rc_index.class</code>. Indicates
     * the type of content that was reported
     */
    public final TableField<CoreRcIndexRecord, String> CLASS = createField(DSL.name("class"), SQLDataType.VARCHAR(255).nullable(false).defaultValue(DSL.inline("''", SQLDataType.VARCHAR)), this, "Indicates the type of content that was reported");

    /**
     * The column <code>paradise_forums.core_rc_index.content_id</code>. The ID
     * number of the content that was reported.
     */
    public final TableField<CoreRcIndexRecord, ULong> CONTENT_ID = createField(DSL.name("content_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINTUNSIGNED)), this, "The ID number of the content that was reported.");

    /**
     * The column <code>paradise_forums.core_rc_index.perm_id</code>. The ID
     * number from the core_permission_index table which indicates who can view
     * this report.
     */
    public final TableField<CoreRcIndexRecord, ULong> PERM_ID = createField(DSL.name("perm_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.inline("NULL", SQLDataType.BIGINTUNSIGNED)), this, "The ID number from the core_permission_index table which indicates who can view this report.");

    /**
     * The column <code>paradise_forums.core_rc_index.status</code>. 1 = New
     * report. 2 = Under Review. 3 = Complete. 4 = Closed.
     */
    public final TableField<CoreRcIndexRecord, CoreRcIndexStatus> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR(1).nullable(false).defaultValue(DSL.inline("'1'", SQLDataType.VARCHAR)).asEnumDataType(me.aa07.paradise.taskdaemon.database.forums.enums.CoreRcIndexStatus.class), this, "1 = New report. 2 = Under Review. 3 = Complete. 4 = Closed.");

    /**
     * The column <code>paradise_forums.core_rc_index.num_reports</code>. Number
     * of reports received.
     */
    public final TableField<CoreRcIndexRecord, UInteger> NUM_REPORTS = createField(DSL.name("num_reports"), SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(DSL.inline("1", SQLDataType.INTEGERUNSIGNED)), this, "Number of reports received.");

    /**
     * The column <code>paradise_forums.core_rc_index.num_comments</code>.
     * Number of comments moderators have made on this report.
     */
    public final TableField<CoreRcIndexRecord, UInteger> NUM_COMMENTS = createField(DSL.name("num_comments"), SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGERUNSIGNED)), this, "Number of comments moderators have made on this report.");

    /**
     * The column <code>paradise_forums.core_rc_index.first_report_by</code>.
     * The ID number of the member who submitted the first report.
     */
    public final TableField<CoreRcIndexRecord, ULong> FIRST_REPORT_BY = createField(DSL.name("first_report_by"), SQLDataType.BIGINTUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINTUNSIGNED)), this, "The ID number of the member who submitted the first report.");

    /**
     * The column <code>paradise_forums.core_rc_index.first_report_date</code>.
     * Unix timestamp of when the first report was submitted.
     */
    public final TableField<CoreRcIndexRecord, Integer> FIRST_REPORT_DATE = createField(DSL.name("first_report_date"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "Unix timestamp of when the first report was submitted.");

    /**
     * The column <code>paradise_forums.core_rc_index.last_updated</code>. Unix
     * timestamp of the last time a comment or report was made (for read/unread
     * marking)
     */
    public final TableField<CoreRcIndexRecord, Integer> LAST_UPDATED = createField(DSL.name("last_updated"), SQLDataType.INTEGER.defaultValue(DSL.inline("NULL", SQLDataType.INTEGER)), this, "Unix timestamp of the last time a comment or report was made (for read/unread marking)");

    /**
     * The column <code>paradise_forums.core_rc_index.author</code>. The ID
     * number of the user who submitted the reported content.
     */
    public final TableField<CoreRcIndexRecord, ULong> AUTHOR = createField(DSL.name("author"), SQLDataType.BIGINTUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINTUNSIGNED)), this, "The ID number of the user who submitted the reported content.");

    /**
     * The column
     * <code>paradise_forums.core_rc_index.auto_moderation_exempt</code>.
     */
    public final TableField<CoreRcIndexRecord, UInteger> AUTO_MODERATION_EXEMPT = createField(DSL.name("auto_moderation_exempt"), SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>paradise_forums.core_rc_index.item_id</code>.
     */
    public final TableField<CoreRcIndexRecord, UInteger> ITEM_ID = createField(DSL.name("item_id"), SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGERUNSIGNED)), this, "");

    /**
     * The column <code>paradise_forums.core_rc_index.node_id</code>.
     */
    public final TableField<CoreRcIndexRecord, UInteger> NODE_ID = createField(DSL.name("node_id"), SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGERUNSIGNED)), this, "");

    private CoreRcIndex(Name alias, Table<CoreRcIndexRecord> aliased) {
        this(alias, aliased, null);
    }

    private CoreRcIndex(Name alias, Table<CoreRcIndexRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.core_rc_index</code> table
     * reference
     */
    public CoreRcIndex(String alias) {
        this(DSL.name(alias), CORE_RC_INDEX);
    }

    /**
     * Create an aliased <code>paradise_forums.core_rc_index</code> table
     * reference
     */
    public CoreRcIndex(Name alias) {
        this(alias, CORE_RC_INDEX);
    }

    /**
     * Create a <code>paradise_forums.core_rc_index</code> table reference
     */
    public CoreRcIndex() {
        this(DSL.name("core_rc_index"), null);
    }

    public <O extends Record> CoreRcIndex(Table<O> child, ForeignKey<O, CoreRcIndexRecord> key) {
        super(child, key, CORE_RC_INDEX);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.CORE_RC_INDEX_AUTHOR, Indexes.CORE_RC_INDEX_CLASS_NODE, Indexes.CORE_RC_INDEX_CONTENT_LOOKUP, Indexes.CORE_RC_INDEX_FIRST_REPORT_BY, Indexes.CORE_RC_INDEX_REPORT_COUNT);
    }

    @Override
    public Identity<CoreRcIndexRecord, ULong> getIdentity() {
        return (Identity<CoreRcIndexRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<CoreRcIndexRecord> getPrimaryKey() {
        return Keys.KEY_CORE_RC_INDEX_PRIMARY;
    }

    @Override
    public CoreRcIndex as(String alias) {
        return new CoreRcIndex(DSL.name(alias), this);
    }

    @Override
    public CoreRcIndex as(Name alias) {
        return new CoreRcIndex(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreRcIndex rename(String name) {
        return new CoreRcIndex(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreRcIndex rename(Name name) {
        return new CoreRcIndex(name, null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<ULong, String, ULong, ULong, CoreRcIndexStatus, UInteger, UInteger, ULong, Integer, Integer, ULong, UInteger, UInteger, UInteger> fieldsRow() {
        return (Row14) super.fieldsRow();
    }
}
