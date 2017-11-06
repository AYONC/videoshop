package com.ridi.domain.video.dto

import com.ridi.domain.video.model.VideoPrice
import java.util.*
import javax.validation.constraints.NotEmpty


data class AddVideoPriceRequest(
        @NotEmpty
        private val price: Int,
        @NotEmpty
        private val startedAt: Date
) {
    fun toEntity() = VideoPrice(price = price, startedAt = startedAt)
}
