/*
 * This file is generated by jOOQ.
*/
package com.ridi.generated.tables;


import com.ridi.generated.Keys;
import com.ridi.generated.Tmp;
import com.ridi.generated.tables.records.TaskCommentRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class TaskComment extends TableImpl<TaskCommentRecord> {

    private static final long serialVersionUID = -1075954897;

    /**
     * The reference instance of <code>tmp.task_comment</code>
     */
    public static final TaskComment TASK_COMMENT = new TaskComment();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TaskCommentRecord> getRecordType() {
        return TaskCommentRecord.class;
    }

    /**
     * The column <code>tmp.task_comment.id</code>.
     */
    public final TableField<TaskCommentRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>tmp.task_comment.content</code>.
     */
    public final TableField<TaskCommentRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>tmp.task_comment.created_at</code>.
     */
    public final TableField<TaskCommentRecord, Timestamp> CREATED_AT = createField("created_at", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>tmp.task_comment.task_id</code>.
     */
    public final TableField<TaskCommentRecord, Long> TASK_ID = createField("task_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>tmp.task_comment</code> table reference
     */
    public TaskComment() {
        this("task_comment", null);
    }

    /**
     * Create an aliased <code>tmp.task_comment</code> table reference
     */
    public TaskComment(String alias) {
        this(alias, TASK_COMMENT);
    }

    private TaskComment(String alias, Table<TaskCommentRecord> aliased) {
        this(alias, aliased, null);
    }

    private TaskComment(String alias, Table<TaskCommentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Tmp.TMP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TaskCommentRecord> getPrimaryKey() {
        return Keys.KEY_TASK_COMMENT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TaskCommentRecord>> getKeys() {
        return Arrays.<UniqueKey<TaskCommentRecord>>asList(Keys.KEY_TASK_COMMENT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskComment as(String alias) {
        return new TaskComment(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TaskComment rename(String name) {
        return new TaskComment(name, null);
    }
}