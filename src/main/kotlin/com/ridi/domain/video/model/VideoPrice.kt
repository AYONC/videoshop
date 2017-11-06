package com.ridi.domain.video.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "video_price")
data class VideoPrice(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
        @Column @NotNull val price: Int = 0,

        @ManyToOne(targetEntity = Video::class)
        @JoinColumn(name = "video_id")
        @NotNull
        val video: Video,

        @Column(name = "started_at") @NotNull val startedAt: Date = Date(),
        @Column(name = "created_at") @NotNull val createdAt: Date = Date()
) {
    fun isCurrentPrice(): Boolean {
        return false
    }
}
