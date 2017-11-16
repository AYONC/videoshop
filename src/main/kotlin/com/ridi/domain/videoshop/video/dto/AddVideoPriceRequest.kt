package com.ridi.domain.videoshop.video.dto

import com.ridi.domain.videoshop.video.model.VideoPrice
import java.util.*
import javax.validation.constraints.NotEmpty


data class AddVideoPriceRequest(
        @NotEmpty
        private val price: Int
) {
    fun toEntity() = VideoPrice(price = price)
}
