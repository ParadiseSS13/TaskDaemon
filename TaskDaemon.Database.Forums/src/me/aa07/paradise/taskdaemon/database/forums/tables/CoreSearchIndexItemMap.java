/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import java.util.Arrays;
import java.util.List;

import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.CoreSearchIndexItemMapRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
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
public class CoreSearchIndexItemMap extends TableImpl<CoreSearchIndexItemMapRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>paradise_forums.core_search_index_item_map</code>
     */
    public static final CoreSearchIndexItemMap CORE_SEARCH_INDEX_ITEM_MAP = new CoreSearchIndexItemMap();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoreSearchIndexItemMapRecord> getRecordType() {
        return CoreSearchIndexItemMapRecord.class;
    }

    /**
     * The column
     * <code>paradise_forums.core_search_index_item_map.index_author_id</code>.
     */
    public final TableField<CoreSearchIndexItemMapRecord, Integer> INDEX_AUTHOR_ID = createField(DSL.name("index_author_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_search_index_item_map.index_item_id</code>.
     */
    public final TableField<CoreSearchIndexItemMapRecord, Integer> INDEX_ITEM_ID = createField(DSL.name("index_item_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_search_index_item_map.index_class</code>.
     */
    public final TableField<CoreSearchIndexItemMapRecord, String> INDEX_CLASS = createField(DSL.name("index_class"), SQLDataType.VARCHAR(80).nullable(false).defaultValue(DSL.inline("''", SQLDataType.VARCHAR)), this, "");

    private CoreSearchIndexItemMap(Name alias, Table<CoreSearchIndexItemMapRecord> aliased) {
        this(alias, aliased, null);
    }

    private CoreSearchIndexItemMap(Name alias, Table<CoreSearchIndexItemMapRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.core_search_index_item_map</code>
     * table reference
     */
    public CoreSearchIndexItemMap(String alias) {
        this(DSL.name(alias), CORE_SEARCH_INDEX_ITEM_MAP);
    }

    /**
     * Create an aliased <code>paradise_forums.core_search_index_item_map</code>
     * table reference
     */
    public CoreSearchIndexItemMap(Name alias) {
        this(alias, CORE_SEARCH_INDEX_ITEM_MAP);
    }

    /**
     * Create a <code>paradise_forums.core_search_index_item_map</code> table
     * reference
     */
    public CoreSearchIndexItemMap() {
        this(DSL.name("core_search_index_item_map"), null);
    }

    public <O extends Record> CoreSearchIndexItemMap(Table<O> child, ForeignKey<O, CoreSearchIndexItemMapRecord> key) {
        super(child, key, CORE_SEARCH_INDEX_ITEM_MAP);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public List<UniqueKey<CoreSearchIndexItemMapRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_CORE_SEARCH_INDEX_ITEM_MAP_SEARCH_MAP);
    }

    @Override
    public CoreSearchIndexItemMap as(String alias) {
        return new CoreSearchIndexItemMap(DSL.name(alias), this);
    }

    @Override
    public CoreSearchIndexItemMap as(Name alias) {
        return new CoreSearchIndexItemMap(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreSearchIndexItemMap rename(String name) {
        return new CoreSearchIndexItemMap(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreSearchIndexItemMap rename(Name name) {
        return new CoreSearchIndexItemMap(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
