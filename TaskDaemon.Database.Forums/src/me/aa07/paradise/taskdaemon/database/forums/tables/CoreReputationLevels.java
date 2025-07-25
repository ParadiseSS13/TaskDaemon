/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.CoreReputationLevelsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
public class CoreReputationLevels extends TableImpl<CoreReputationLevelsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>paradise_forums.core_reputation_levels</code>
     */
    public static final CoreReputationLevels CORE_REPUTATION_LEVELS = new CoreReputationLevels();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoreReputationLevelsRecord> getRecordType() {
        return CoreReputationLevelsRecord.class;
    }

    /**
     * The column <code>paradise_forums.core_reputation_levels.level_id</code>.
     */
    public final TableField<CoreReputationLevelsRecord, Integer> LEVEL_ID = createField(DSL.name("level_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>paradise_forums.core_reputation_levels.level_points</code>.
     */
    public final TableField<CoreReputationLevelsRecord, Integer> LEVEL_POINTS = createField(DSL.name("level_points"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_reputation_levels.level_image</code>.
     */
    public final TableField<CoreReputationLevelsRecord, String> LEVEL_IMAGE = createField(DSL.name("level_image"), SQLDataType.VARCHAR(255).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    private CoreReputationLevels(Name alias, Table<CoreReputationLevelsRecord> aliased) {
        this(alias, aliased, null);
    }

    private CoreReputationLevels(Name alias, Table<CoreReputationLevelsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.core_reputation_levels</code>
     * table reference
     */
    public CoreReputationLevels(String alias) {
        this(DSL.name(alias), CORE_REPUTATION_LEVELS);
    }

    /**
     * Create an aliased <code>paradise_forums.core_reputation_levels</code>
     * table reference
     */
    public CoreReputationLevels(Name alias) {
        this(alias, CORE_REPUTATION_LEVELS);
    }

    /**
     * Create a <code>paradise_forums.core_reputation_levels</code> table
     * reference
     */
    public CoreReputationLevels() {
        this(DSL.name("core_reputation_levels"), null);
    }

    public <O extends Record> CoreReputationLevels(Table<O> child, ForeignKey<O, CoreReputationLevelsRecord> key) {
        super(child, key, CORE_REPUTATION_LEVELS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public Identity<CoreReputationLevelsRecord, Integer> getIdentity() {
        return (Identity<CoreReputationLevelsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<CoreReputationLevelsRecord> getPrimaryKey() {
        return Keys.KEY_CORE_REPUTATION_LEVELS_PRIMARY;
    }

    @Override
    public CoreReputationLevels as(String alias) {
        return new CoreReputationLevels(DSL.name(alias), this);
    }

    @Override
    public CoreReputationLevels as(Name alias) {
        return new CoreReputationLevels(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreReputationLevels rename(String name) {
        return new CoreReputationLevels(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreReputationLevels rename(Name name) {
        return new CoreReputationLevels(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
