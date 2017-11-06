package com.ridi.domain.video.dto

import javax.validation.constraints.NotEmpty


data class UpdateVideoRequest(
    @NotEmpty
    val title: String,
    @NotEmpty
    val description: String
)
