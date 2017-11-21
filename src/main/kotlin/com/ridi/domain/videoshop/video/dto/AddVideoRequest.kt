package com.ridi.domain.videoshop.video.dto

import com.ridi.domain.videoshop.video.model.Video
import com.ridi.domain.videoshop.video.util.AgeRating
import org.springframework.web.bind.annotation.RequestAttribute
import javax.validation.constraints.NotEmpty

data class AddVideoRequest(
        @NotEmpty
        private val title: String,
        @NotEmpty
        private val description: String,
        @NotEmpty
        private val addRating: AgeRating
) {
    fun toEntity() = Video(title = title, description = description, rating = addRating)
}
