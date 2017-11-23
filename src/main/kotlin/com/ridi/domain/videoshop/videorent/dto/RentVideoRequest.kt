package com.ridi.domain.videoshop.videorent.dto

data class RentVideoRequest(
    val videoId: Long,
    val rentDays: Int = 3,
    val isIgnoreRentBefore: Boolean = false
)
