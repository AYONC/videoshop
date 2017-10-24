/*
 * This file is generated by jOOQ.
*/
package com.ridi.generated.tables;


import com.ridi.generated.Keys;
import com.ridi.generated.Tmp;
import com.ridi.generated.tables.records.TaskRecord;

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
public class Task extends TableImpl<TaskRecord> {

    private static final long serialVersionUID = 1180796142;

    /**
     * The reference instance of <code>tmp.task</code>
     */
    public static final Task TASK = new Task();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TaskRecord> getRecordType() {
        return TaskRecord.class;
    }

    /**
     * The column <code>tmp.task.id</code>.
     */
    public final TableField<TaskRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>tmp.task.content</code>.
     */
    public final TableField<TaskRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>tmp.task.created_at</code>.
     */
    public final TableField<TaskRecord, Timestamp> CREATED_AT = createField("created_at", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>tmp.task.title</code>.
     */
    public final TableField<TaskRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>tmp.task.member_id</code>.
     */
    public final TableField<TaskRecord, Long> MEMBER_ID = createField("member_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>tmp.task.is_completed</code>.
     */
    public final TableField<TaskRecord, Boolean> IS_COMPLETED = createField("is_completed", org.jooq.impl.SQLDataType.BIT, this, "");

    /**
     * Create a <code>tmp.task</code> table reference
     */
    public Task() {
        this("task", null);
    }

    /**
     * Create an aliased <code>tmp.task</code> table reference
     */
    public Task(String alias) {
        this(alias, TASK);
    }

    private Task(String alias, Table<TaskRecord> aliased) {
        this(alias, aliased, null);
    }

    private Task(String alias, Table<TaskRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<TaskRecord> getPrimaryKey() {
        return Keys.KEY_TASK_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TaskRecord>> getKeys() {
        return Arrays.<UniqueKey<TaskRecord>>asList(Keys.KEY_TASK_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task as(String alias) {
        return new Task(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Task rename(String name) {
        return new Task(name, null);
    }
}
