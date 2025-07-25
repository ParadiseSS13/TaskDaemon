/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import java.util.Arrays;
import java.util.List;

import me.aa07.paradise.taskdaemon.database.forums.Indexes;
import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.CoreItemMarkersRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
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
public class CoreItemMarkers extends TableImpl<CoreItemMarkersRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>paradise_forums.core_item_markers</code>
     */
    public static final CoreItemMarkers CORE_ITEM_MARKERS = new CoreItemMarkers();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoreItemMarkersRecord> getRecordType() {
        return CoreItemMarkersRecord.class;
    }

    /**
     * The column <code>paradise_forums.core_item_markers.item_key</code>.
     */
    public final TableField<CoreItemMarkersRecord, String> ITEM_KEY = createField(DSL.name("item_key"), SQLDataType.CHAR(32).nullable(false).defaultValue(DSL.inline("''", SQLDataType.CHAR)), this, "");

    /**
     * The column <code>paradise_forums.core_item_markers.item_member_id</code>.
     */
    public final TableField<CoreItemMarkersRecord, ULong> ITEM_MEMBER_ID = createField(DSL.name("item_member_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>paradise_forums.core_item_markers.item_app</code>.
     */
    public final TableField<CoreItemMarkersRecord, String> ITEM_APP = createField(DSL.name("item_app"), SQLDataType.VARCHAR(100).nullable(false).defaultValue(DSL.inline("'core'", SQLDataType.VARCHAR)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_item_markers.item_read_array</code>.
     */
    public final TableField<CoreItemMarkersRecord, String> ITEM_READ_ARRAY = createField(DSL.name("item_read_array"), SQLDataType.CLOB.defaultValue(DSL.inline("NULL", SQLDataType.CLOB)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_item_markers.item_global_reset</code>.
     */
    public final TableField<CoreItemMarkersRecord, Integer> ITEM_GLOBAL_RESET = createField(DSL.name("item_global_reset"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>paradise_forums.core_item_markers.item_app_key_1</code>.
     */
    public final TableField<CoreItemMarkersRecord, Integer> ITEM_APP_KEY_1 = createField(DSL.name("item_app_key_1"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>paradise_forums.core_item_markers.item_app_key_2</code>.
     */
    public final TableField<CoreItemMarkersRecord, Integer> ITEM_APP_KEY_2 = createField(DSL.name("item_app_key_2"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>paradise_forums.core_item_markers.item_app_key_3</code>.
     */
    public final TableField<CoreItemMarkersRecord, Integer> ITEM_APP_KEY_3 = createField(DSL.name("item_app_key_3"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    private CoreItemMarkers(Name alias, Table<CoreItemMarkersRecord> aliased) {
        this(alias, aliased, null);
    }

    private CoreItemMarkers(Name alias, Table<CoreItemMarkersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.core_item_markers</code> table
     * reference
     */
    public CoreItemMarkers(String alias) {
        this(DSL.name(alias), CORE_ITEM_MARKERS);
    }

    /**
     * Create an aliased <code>paradise_forums.core_item_markers</code> table
     * reference
     */
    public CoreItemMarkers(Name alias) {
        this(alias, CORE_ITEM_MARKERS);
    }

    /**
     * Create a <code>paradise_forums.core_item_markers</code> table reference
     */
    public CoreItemMarkers() {
        this(DSL.name("core_item_markers"), null);
    }

    public <O extends Record> CoreItemMarkers(Table<O> child, ForeignKey<O, CoreItemMarkersRecord> key) {
        super(child, key, CORE_ITEM_MARKERS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.CORE_ITEM_MARKERS_MARKER_INDEX);
    }

    @Override
    public List<UniqueKey<CoreItemMarkersRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_CORE_ITEM_MARKERS_COMBO_KEY);
    }

    @Override
    public CoreItemMarkers as(String alias) {
        return new CoreItemMarkers(DSL.name(alias), this);
    }

    @Override
    public CoreItemMarkers as(Name alias) {
        return new CoreItemMarkers(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreItemMarkers rename(String name) {
        return new CoreItemMarkers(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreItemMarkers rename(Name name) {
        return new CoreItemMarkers(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, ULong, String, String, Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
