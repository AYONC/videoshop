/*
 * This file is generated by jOOQ.
*/
package com.ridi.generated.tables.records;


import com.ridi.generated.tables.PostComment;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
public class PostCommentRecord extends UpdatableRecordImpl<PostCommentRecord> implements Record5<Long, String, Timestamp, String, Long> {

    private static final long serialVersionUID = -1261686116;

    /**
     * Setter for <code>tmp.post_comment.id</code>.
     */
    public PostCommentRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>tmp.post_comment.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>tmp.post_comment.content</code>.
     */
    public PostCommentRecord setContent(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>tmp.post_comment.content</code>.
     */
    public String getContent() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tmp.post_comment.created_at</code>.
     */
    public PostCommentRecord setCreatedAt(Timestamp value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>tmp.post_comment.created_at</code>.
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>tmp.post_comment.user</code>.
     */
    public PostCommentRecord setUser(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>tmp.post_comment.user</code>.
     */
    public String getUser() {
        return (String) get(3);
    }

    /**
     * Setter for <code>tmp.post_comment.post_id</code>.
     */
    public PostCommentRecord setPostId(Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>tmp.post_comment.post_id</code>.
     */
    public Long getPostId() {
        return (Long) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, Timestamp, String, Long> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, Timestamp, String, Long> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return PostComment.POST_COMMENT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return PostComment.POST_COMMENT.CONTENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return PostComment.POST_COMMENT.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return PostComment.POST_COMMENT.USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field5() {
        return PostComment.POST_COMMENT.POST_ID;
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
    public String value4() {
        return getUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value5() {
        return getPostId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostCommentRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostCommentRecord value2(String value) {
        setContent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostCommentRecord value3(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostCommentRecord value4(String value) {
        setUser(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostCommentRecord value5(Long value) {
        setPostId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostCommentRecord values(Long value1, String value2, Timestamp value3, String value4, Long value5) {
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
     * Create a detached PostCommentRecord
     */
    public PostCommentRecord() {
        super(PostComment.POST_COMMENT);
    }

    /**
     * Create a detached, initialised PostCommentRecord
     */
    public PostCommentRecord(Long id, String content, Timestamp createdAt, String user, Long postId) {
        super(PostComment.POST_COMMENT);

        set(0, id);
        set(1, content);
        set(2, createdAt);
        set(3, user);
        set(4, postId);
    }
}
