/*
 * This file is generated by jOOQ.
*/
package com.ridi.generated.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


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
public class TaskComment implements Serializable {

    private static final long serialVersionUID = -735271900;

    private final Long      id;
    private final String    content;
    private final Timestamp createdAt;
    private final Long      taskId;

    public TaskComment(TaskComment value) {
        this.id = value.id;
        this.content = value.content;
        this.createdAt = value.createdAt;
        this.taskId = value.taskId;
    }

    public TaskComment(
        Long      id,
        String    content,
        Timestamp createdAt,
        Long      taskId
    ) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.taskId = taskId;
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TaskComment (");

        sb.append(id);
        sb.append(", ").append(content);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(taskId);

        sb.append(")");
        return sb.toString();
    }
}
