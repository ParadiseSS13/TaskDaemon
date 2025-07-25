/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables;


import me.aa07.paradise.taskdaemon.database.forums.Keys;
import me.aa07.paradise.taskdaemon.database.forums.ParadiseForums;
import me.aa07.paradise.taskdaemon.database.forums.tables.records.CoreProfileStepsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
import org.jooq.types.UInteger;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreProfileSteps extends TableImpl<CoreProfileStepsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>paradise_forums.core_profile_steps</code>
     */
    public static final CoreProfileSteps CORE_PROFILE_STEPS = new CoreProfileSteps();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoreProfileStepsRecord> getRecordType() {
        return CoreProfileStepsRecord.class;
    }

    /**
     * The column <code>paradise_forums.core_profile_steps.step_id</code>. ID
     * Number
     */
    public final TableField<CoreProfileStepsRecord, ULong> STEP_ID = createField(DSL.name("step_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "ID Number");

    /**
     * The column
     * <code>paradise_forums.core_profile_steps.step_completion_act</code>.
     */
    public final TableField<CoreProfileStepsRecord, String> STEP_COMPLETION_ACT = createField(DSL.name("step_completion_act"), SQLDataType.VARCHAR(255).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>paradise_forums.core_profile_steps.step_required</code>.
     */
    public final TableField<CoreProfileStepsRecord, Boolean> STEP_REQUIRED = createField(DSL.name("step_required"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_profile_steps.step_extension</code>.
     */
    public final TableField<CoreProfileStepsRecord, String> STEP_EXTENSION = createField(DSL.name("step_extension"), SQLDataType.VARCHAR(255).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column
     * <code>paradise_forums.core_profile_steps.step_subcompletion_act</code>.
     */
    public final TableField<CoreProfileStepsRecord, String> STEP_SUBCOMPLETION_ACT = createField(DSL.name("step_subcompletion_act"), SQLDataType.CLOB.defaultValue(DSL.inline("NULL", SQLDataType.CLOB)), this, "");

    /**
     * The column <code>paradise_forums.core_profile_steps.step_position</code>.
     */
    public final TableField<CoreProfileStepsRecord, UInteger> STEP_POSITION = createField(DSL.name("step_position"), SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGERUNSIGNED)), this, "");

    private CoreProfileSteps(Name alias, Table<CoreProfileStepsRecord> aliased) {
        this(alias, aliased, null);
    }

    private CoreProfileSteps(Name alias, Table<CoreProfileStepsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>paradise_forums.core_profile_steps</code> table
     * reference
     */
    public CoreProfileSteps(String alias) {
        this(DSL.name(alias), CORE_PROFILE_STEPS);
    }

    /**
     * Create an aliased <code>paradise_forums.core_profile_steps</code> table
     * reference
     */
    public CoreProfileSteps(Name alias) {
        this(alias, CORE_PROFILE_STEPS);
    }

    /**
     * Create a <code>paradise_forums.core_profile_steps</code> table reference
     */
    public CoreProfileSteps() {
        this(DSL.name("core_profile_steps"), null);
    }

    public <O extends Record> CoreProfileSteps(Table<O> child, ForeignKey<O, CoreProfileStepsRecord> key) {
        super(child, key, CORE_PROFILE_STEPS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ParadiseForums.PARADISE_FORUMS;
    }

    @Override
    public Identity<CoreProfileStepsRecord, ULong> getIdentity() {
        return (Identity<CoreProfileStepsRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<CoreProfileStepsRecord> getPrimaryKey() {
        return Keys.KEY_CORE_PROFILE_STEPS_PRIMARY;
    }

    @Override
    public CoreProfileSteps as(String alias) {
        return new CoreProfileSteps(DSL.name(alias), this);
    }

    @Override
    public CoreProfileSteps as(Name alias) {
        return new CoreProfileSteps(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreProfileSteps rename(String name) {
        return new CoreProfileSteps(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreProfileSteps rename(Name name) {
        return new CoreProfileSteps(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<ULong, String, Boolean, String, String, UInteger> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
