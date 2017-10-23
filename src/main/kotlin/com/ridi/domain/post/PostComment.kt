package com.ridi.domain.post

import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "post_comment")
data class PostComment (
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) private val id: Long = 0,
    @Column private val content: String,
    @Column(name = "created_at") private val createdAt: Date = Date(),
    @Column private val user: String,
    @JoinColumn(name = "post_id") @ManyToOne(targetEntity = Post::class, fetch = FetchType.LAZY) private val post: Post
)
