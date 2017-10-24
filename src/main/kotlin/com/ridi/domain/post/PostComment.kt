package com.ridi.domain.post

import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "post_comment")
data class PostComment (
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column val content: String,
    @Column(name = "created_at") val createdAt: Date = Date(),
    @Column val user: String,
    @JoinColumn(name = "post_id") @ManyToOne(targetEntity = Post::class) val post: Post
)
