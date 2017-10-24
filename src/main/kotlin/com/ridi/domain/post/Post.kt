package com.ridi.domain.post

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "post")
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column val content: String,
    @Column val user: String,
    @Column(name = "created_at") val createdAt: Date = Date()
)
