/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.CorePfieldsContentRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
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
public class CorePfieldsContent extends TableImpl<CorePfieldsContentRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>paradise_forums.core_pfields_content</code>
     */
    public static final CorePfieldsContent CORE_PFIELDS_CONTENT = new CorePfieldsContent();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CorePfieldsContentRecord> getRecordType() {
        return CorePfieldsContentRecord.class;
    }

    /**
     * The column <code>paradise_forums.core_pfields_content.member_id</code>.
     */
    public final TableField<CorePfieldsContentRecord, ULong> MEMBER_ID = createField(DSL.name("member_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>paradise_forums.core_pfields_content.field_1</code>.
     */
    public final TableField<CorePfieldsContentRecord, String> FIELD_1 = createField(DSL.name("field_1"), SQLDataType.CLOB.defaultValue(DSL.inline("NULL", SQLDataType.CLOB)), this, "");

    /**
     * The column <code>paradise_forums.core_pfields_content.field_10</code>.
     */
    public final TableField<CorePfieldsContentRecord, String> FIELD_10 = createField(DSL.name("field_10"), SQLDataType.CLOB.defaultValue(DSL.inline("NULL", SQLDataType.CLOB)), this, "");

    /**
     * The column <code>paradise_forums.core_pfields_content.field_13</code>.
     */
    public final TableField<CorePfieldsContentRecord, String> FIELD_13 = createField(DSL.name("field_13"), SQLDataType.CLOB.defaultValue(DSL.inline("NULL", SQLDataType.CLOB)), this, "");

    private CorePfieldsContent(Name alias, Table<CorePfieldsContentRecord> aliased) {
        this(alias, aliased, null);
    }

    private CorePfieldsContent(Name alias, Table<CorePfieldsContentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.core_pfields_content</code> table
     * reference
     */
    public CorePfieldsContent(String alias) {
        this(DSL.name(alias), CORE_PFIELDS_CONTENT);
    }

    /**
     * Create an aliased <code>paradise_forums.core_pfields_content</code> table
     * reference
     */
    public CorePfieldsContent(Name alias) {
        this(alias, CORE_PFIELDS_CONTENT);
    }

    /**
     * Create a <code>paradise_forums.core_pfields_content</code> table
     * reference
     */
    public CorePfieldsContent() {
        this(DSL.name("core_pfields_content"), null);
    }

    public <O extends Record> CorePfieldsContent(Table<O> child, ForeignKey<O, CorePfieldsContentRecord> key) {
        super(child, key, CORE_PFIELDS_CONTENT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public UniqueKey<CorePfieldsContentRecord> getPrimaryKey() {
        return Keys.KEY_CORE_PFIELDS_CONTENT_PRIMARY;
    }

    @Override
    public CorePfieldsContent as(String alias) {
        return new CorePfieldsContent(DSL.name(alias), this);
    }

    @Override
    public CorePfieldsContent as(Name alias) {
        return new CorePfieldsContent(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CorePfieldsContent rename(String name) {
        return new CorePfieldsContent(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CorePfieldsContent rename(Name name) {
        return new CorePfieldsContent(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<ULong, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
