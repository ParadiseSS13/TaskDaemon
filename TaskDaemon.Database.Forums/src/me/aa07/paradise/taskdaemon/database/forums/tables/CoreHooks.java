/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import java.util.Arrays;
import java.util.List;

import me.aa07.paradise.taskdaemon.database.forums.Indexes;
import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.CoreHooksRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
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
public class CoreHooks extends TableImpl<CoreHooksRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>paradise_forums.core_hooks</code>
     */
    public static final CoreHooks CORE_HOOKS = new CoreHooks();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoreHooksRecord> getRecordType() {
        return CoreHooksRecord.class;
    }

    /**
     * The column <code>paradise_forums.core_hooks.id</code>. ID Number
     */
    public final TableField<CoreHooksRecord, ULong> ID = createField(DSL.name("id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "ID Number");

    /**
     * The column <code>paradise_forums.core_hooks.plugin</code>. Plugin ID, if
     * belongs to a plugin
     */
    public final TableField<CoreHooksRecord, ULong> PLUGIN = createField(DSL.name("plugin"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.inline("NULL", SQLDataType.BIGINTUNSIGNED)), this, "Plugin ID, if belongs to a plugin");

    /**
     * The column <code>paradise_forums.core_hooks.app</code>. Application key,
     * if belongs to app
     */
    public final TableField<CoreHooksRecord, String> APP = createField(DSL.name("app"), SQLDataType.VARCHAR(255).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "Application key, if belongs to app");

    /**
     * The column <code>paradise_forums.core_hooks.type</code>. C for code, S
     * for skin
     */
    public final TableField<CoreHooksRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.CHAR(1).nullable(false).defaultValue(DSL.inline("''", SQLDataType.CHAR)), this, "C for code, S for skin");

    /**
     * The column <code>paradise_forums.core_hooks.class</code>. The class the
     * hook overrides
     */
    public final TableField<CoreHooksRecord, String> CLASS = createField(DSL.name("class"), SQLDataType.VARCHAR(255).nullable(false).defaultValue(DSL.inline("''", SQLDataType.VARCHAR)), this, "The class the hook overrides");

    /**
     * The column <code>paradise_forums.core_hooks.filename</code>. The filename
     * on disk
     */
    public final TableField<CoreHooksRecord, String> FILENAME = createField(DSL.name("filename"), SQLDataType.VARCHAR(32).nullable(false).defaultValue(DSL.inline("''", SQLDataType.VARCHAR)), this, "The filename on disk");

    private CoreHooks(Name alias, Table<CoreHooksRecord> aliased) {
        this(alias, aliased, null);
    }

    private CoreHooks(Name alias, Table<CoreHooksRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.core_hooks</code> table reference
     */
    public CoreHooks(String alias) {
        this(DSL.name(alias), CORE_HOOKS);
    }

    /**
     * Create an aliased <code>paradise_forums.core_hooks</code> table reference
     */
    public CoreHooks(Name alias) {
        this(alias, CORE_HOOKS);
    }

    /**
     * Create a <code>paradise_forums.core_hooks</code> table reference
     */
    public CoreHooks() {
        this(DSL.name("core_hooks"), null);
    }

    public <O extends Record> CoreHooks(Table<O> child, ForeignKey<O, CoreHooksRecord> key) {
        super(child, key, CORE_HOOKS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.CORE_HOOKS_APP, Indexes.CORE_HOOKS_PLUGIN);
    }

    @Override
    public Identity<CoreHooksRecord, ULong> getIdentity() {
        return (Identity<CoreHooksRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<CoreHooksRecord> getPrimaryKey() {
        return Keys.KEY_CORE_HOOKS_PRIMARY;
    }

    @Override
    public CoreHooks as(String alias) {
        return new CoreHooks(DSL.name(alias), this);
    }

    @Override
    public CoreHooks as(Name alias) {
        return new CoreHooks(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreHooks rename(String name) {
        return new CoreHooks(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreHooks rename(Name name) {
        return new CoreHooks(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<ULong, ULong, String, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
