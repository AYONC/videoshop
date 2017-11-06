package com.ridi.domain.video.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "video")
data class Video(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull val title: String,
    @Column @NotNull val description: String,
    @Column(name = "cover_path", nullable = true) val coverPath: String? = null,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
)
