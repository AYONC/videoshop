package com.ridi.domain.videoshop.video.model

import com.ridi.common.EntityListener
import com.ridi.domain.videoshop.video.exception.VideoNotOpenedException
import com.ridi.domain.videoshop.video.exception.VideoNotPassAgeRatingException
import com.ridi.domain.videoshop.video.util.AgeRating
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@EntityListeners(EntityListener::class)
@Table(name = "video")
data class Video(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
        @Column @NotNull var title: String,
        @Column @NotNull var description: String,
        @Column(name = "age_rating") @NotNull @Enumerated(EnumType.ORDINAL) var rating: AgeRating,
        @Column(name = "cover_path", nullable = true) var coverPath: String? = null,
        @Column(name = "is_opened", nullable = false) var isOpened: Boolean = false,
        @Column(name = "created_at") @NotNull val createdAt: Date = Date()
) {
    fun assertIsOpened() {
        if (!isOpened) {
            throw VideoNotOpenedException()
        }
    }

    fun assertIsPassing(age: Int?) {
        if (!rating.isPassing(age)) {
            throw VideoNotPassAgeRatingException()
        }
    }
}
