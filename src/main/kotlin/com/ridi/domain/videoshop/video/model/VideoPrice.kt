package com.ridi.domain.videoshop.video.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "video_price")
data class VideoPrice(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
        @Column(nullable = false) @NotNull val price: Int = 0,

        @ManyToOne(targetEntity = Video::class)
        @JoinColumn(name = "video_id")
        var video: Video? = null,

        @Column(name = "started_at", nullable = false) @NotNull val startedAt: Date = Date(),
        @Column(name = "created_at") @NotNull val createdAt: Date = Date()
)
