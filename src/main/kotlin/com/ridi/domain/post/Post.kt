package com.ridi.domain.post

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "post")
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private val id: Long = 0,
    @Column private val content: String,
    @Column(name = "created_at") val createdAt: Date = Date(),
    @Column val user: String
)
