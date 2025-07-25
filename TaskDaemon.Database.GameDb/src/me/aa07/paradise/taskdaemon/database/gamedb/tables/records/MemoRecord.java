/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.gamedb.tables.records;


import java.time.LocalDateTime;

import me.aa07.paradise.taskdaemon.database.gamedb.tables.Memo;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MemoRecord extends UpdatableRecordImpl<MemoRecord> implements Record5<String, String, LocalDateTime, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_gamedb.memo.ckey</code>.
     */
    public void setCkey(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_gamedb.memo.ckey</code>.
     */
    public String getCkey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>paradise_gamedb.memo.memotext</code>.
     */
    public void setMemotext(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>paradise_gamedb.memo.memotext</code>.
     */
    public String getMemotext() {
        return (String) get(1);
    }

    /**
     * Setter for <code>paradise_gamedb.memo.timestamp</code>.
     */
    public void setTimestamp(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>paradise_gamedb.memo.timestamp</code>.
     */
    public LocalDateTime getTimestamp() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>paradise_gamedb.memo.last_editor</code>.
     */
    public void setLastEditor(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>paradise_gamedb.memo.last_editor</code>.
     */
    public String getLastEditor() {
        return (String) get(3);
    }

    /**
     * Setter for <code>paradise_gamedb.memo.edits</code>.
     */
    public void setEdits(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>paradise_gamedb.memo.edits</code>.
     */
    public String getEdits() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<String, String, LocalDateTime, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<String, String, LocalDateTime, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Memo.MEMO.CKEY;
    }

    @Override
    public Field<String> field2() {
        return Memo.MEMO.MEMOTEXT;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return Memo.MEMO.TIMESTAMP;
    }

    @Override
    public Field<String> field4() {
        return Memo.MEMO.LAST_EDITOR;
    }

    @Override
    public Field<String> field5() {
        return Memo.MEMO.EDITS;
    }

    @Override
    public String component1() {
        return getCkey();
    }

    @Override
    public String component2() {
        return getMemotext();
    }

    @Override
    public LocalDateTime component3() {
        return getTimestamp();
    }

    @Override
    public String component4() {
        return getLastEditor();
    }

    @Override
    public String component5() {
        return getEdits();
    }

    @Override
    public String value1() {
        return getCkey();
    }

    @Override
    public String value2() {
        return getMemotext();
    }

    @Override
    public LocalDateTime value3() {
        return getTimestamp();
    }

    @Override
    public String value4() {
        return getLastEditor();
    }

    @Override
    public String value5() {
        return getEdits();
    }

    @Override
    public MemoRecord value1(String value) {
        setCkey(value);
        return this;
    }

    @Override
    public MemoRecord value2(String value) {
        setMemotext(value);
        return this;
    }

    @Override
    public MemoRecord value3(LocalDateTime value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public MemoRecord value4(String value) {
        setLastEditor(value);
        return this;
    }

    @Override
    public MemoRecord value5(String value) {
        setEdits(value);
        return this;
    }

    @Override
    public MemoRecord values(String value1, String value2, LocalDateTime value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MemoRecord
     */
    public MemoRecord() {
        super(Memo.MEMO);
    }

    /**
     * Create a detached, initialised MemoRecord
     */
    public MemoRecord(String ckey, String memotext, LocalDateTime timestamp, String lastEditor, String edits) {
        super(Memo.MEMO);

        setCkey(ckey);
        setMemotext(memotext);
        setTimestamp(timestamp);
        setLastEditor(lastEditor);
        setEdits(edits);
    }
}
