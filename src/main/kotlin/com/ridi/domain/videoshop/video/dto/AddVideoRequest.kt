package com.ridi.domain.videoshop.video.dto

import com.ridi.domain.videoshop.video.model.Video
import javax.validation.constraints.NotEmpty

data class AddVideoRequest(
        @NotEmpty
        private val title: String,
        @NotEmpty
        private val description: String
) {
    fun toEntity() = Video(title = title, description = description)
}
