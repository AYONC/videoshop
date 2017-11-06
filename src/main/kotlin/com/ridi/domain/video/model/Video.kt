package com.ridi.domain.video.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "video")
data class Video(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull var title: String,
    @Column @NotNull var description: String,
    @Column(name = "cover_path", nullable = true) var coverPath: String? = null,
    @Column(name = "is_opened", nullable = false) var isOpened: Boolean = false,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
)
