/*
 * This file is generated by jOOQ.
*/
package com.ridi.generated.tables.records;


import com.ridi.generated.tables.MemberTasks;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MemberTasksRecord extends TableRecordImpl<MemberTasksRecord> implements Record2<Long, byte[]> {

    private static final long serialVersionUID = -1536203024;

    /**
     * Setter for <code>tmp.Member_tasks.Member_id</code>.
     */
    public MemberTasksRecord setMemberId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>tmp.Member_tasks.Member_id</code>.
     */
    public Long getMemberId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>tmp.Member_tasks.tasks</code>.
     */
    public MemberTasksRecord setTasks(byte... value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>tmp.Member_tasks.tasks</code>.
     */
    public byte[] getTasks() {
        return (byte[]) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, byte[]> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, byte[]> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return MemberTasks.MEMBER_TASKS.MEMBER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<byte[]> field2() {
        return MemberTasks.MEMBER_TASKS.TASKS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getMemberId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] value2() {
        return getTasks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberTasksRecord value1(Long value) {
        setMemberId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberTasksRecord value2(byte... value) {
        setTasks(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberTasksRecord values(Long value1, byte[] value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MemberTasksRecord
     */
    public MemberTasksRecord() {
        super(MemberTasks.MEMBER_TASKS);
    }

    /**
     * Create a detached, initialised MemberTasksRecord
     */
    public MemberTasksRecord(Long memberId, byte[] tasks) {
        super(MemberTasks.MEMBER_TASKS);

        set(0, memberId);
        set(1, tasks);
    }
}