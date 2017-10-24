/*
 * This file is generated by jOOQ.
*/
package com.ridi.generated.tables.records;


import com.ridi.generated.tables.TaskComment;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class TaskCommentRecord extends UpdatableRecordImpl<TaskCommentRecord> implements Record4<Long, String, Timestamp, Long> {

    private static final long serialVersionUID = 1258563372;

    /**
     * Setter for <code>tmp.task_comment.id</code>.
     */
    public TaskCommentRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>tmp.task_comment.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>tmp.task_comment.content</code>.
     */
    public TaskCommentRecord setContent(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>tmp.task_comment.content</code>.
     */
    public String getContent() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tmp.task_comment.created_at</code>.
     */
    public TaskCommentRecord setCreatedAt(Timestamp value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>tmp.task_comment.created_at</code>.
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>tmp.task_comment.task_id</code>.
     */
    public TaskCommentRecord setTaskId(Long value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>tmp.task_comment.task_id</code>.
     */
    public Long getTaskId() {
        return (Long) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Long, String, Timestamp, Long> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Long, String, Timestamp, Long> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return TaskComment.TASK_COMMENT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TaskComment.TASK_COMMENT.CONTENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return TaskComment.TASK_COMMENT.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field4() {
        return TaskComment.TASK_COMMENT.TASK_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getContent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value4() {
        return getTaskId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskCommentRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskCommentRecord value2(String value) {
        setContent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskCommentRecord value3(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskCommentRecord value4(Long value) {
        setTaskId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskCommentRecord values(Long value1, String value2, Timestamp value3, Long value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TaskCommentRecord
     */
    public TaskCommentRecord() {
        super(TaskComment.TASK_COMMENT);
    }

    /**
     * Create a detached, initialised TaskCommentRecord
     */
    public TaskCommentRecord(Long id, String content, Timestamp createdAt, Long taskId) {
        super(TaskComment.TASK_COMMENT);

        set(0, id);
        set(1, content);
        set(2, createdAt);
        set(3, taskId);
    }
}
