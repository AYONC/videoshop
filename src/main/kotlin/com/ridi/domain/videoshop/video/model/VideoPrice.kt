package com.ridi.domain.videoshop.video.model

import com.ridi.common.EntityListener
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@EntityListeners(EntityListener::class)
@Table(name = "video_price")
data class VideoPrice(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
        @Column(nullable = false) @NotNull val price: Int = 0,

        @ManyToOne(targetEntity = Video::class)
        @JoinColumn(name = "video_id")
        var video: Video? = null,

        @Column(name = "is_active", nullable = false) var isActive: Boolean = false,
        @Column(name = "created_at") @NotNull val createdAt: Date = Date()
)
