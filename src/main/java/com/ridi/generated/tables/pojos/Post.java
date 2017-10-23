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
public class Post implements Serializable {

    private static final long serialVersionUID = -1813852638;

    private final Long      id;
    private final String    content;
    private final Timestamp createdAt;
    private final String    user;

    public Post(Post value) {
        this.id = value.id;
        this.content = value.content;
        this.createdAt = value.createdAt;
        this.user = value.user;
    }

    public Post(
        Long      id,
        String    content,
        Timestamp createdAt,
        String    user
    ) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
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

    public String getUser() {
        return this.user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Post (");

        sb.append(id);
        sb.append(", ").append(content);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(user);

        sb.append(")");
        return sb.toString();
    }
}
