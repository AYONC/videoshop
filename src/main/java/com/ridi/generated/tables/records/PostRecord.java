/*
 * This file is generated by jOOQ.
*/
package com.ridi.generated.tables.records;


import com.ridi.generated.tables.Post;

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
public class PostRecord extends UpdatableRecordImpl<PostRecord> implements Record4<Integer, String, Timestamp, String> {

    private static final long serialVersionUID = 2767635;

    /**
     * Setter for <code>tmp.post.id</code>.
     */
    public PostRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>tmp.post.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tmp.post.content</code>.
     */
    public PostRecord setContent(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>tmp.post.content</code>.
     */
    public String getContent() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tmp.post.created_at</code>.
     */
    public PostRecord setCreatedAt(Timestamp value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>tmp.post.created_at</code>.
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>tmp.post.user</code>.
     */
    public PostRecord setUser(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>tmp.post.user</code>.
     */
    public String getUser() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, String, Timestamp, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, String, Timestamp, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Post.POST.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Post.POST.CONTENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Post.POST.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Post.POST.USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
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
    public PostRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value2(String value) {
        setContent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value3(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value4(String value) {
        setUser(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord values(Integer value1, String value2, Timestamp value3, String value4) {
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
     * Create a detached PostRecord
     */
    public PostRecord() {
        super(Post.POST);
    }

    /**
     * Create a detached, initialised PostRecord
     */
    public PostRecord(Integer id, String content, Timestamp createdAt, String user) {
        super(Post.POST);

        set(0, id);
        set(1, content);
        set(2, createdAt);
        set(3, user);
    }
}
