package com.ridi.domain.post

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "post")
data class Post(
    @Id private val id: Long,
    @Column private val content: String,
    @Column val createdAt: Date = Date(),
    @Column val user: String
) {
}
