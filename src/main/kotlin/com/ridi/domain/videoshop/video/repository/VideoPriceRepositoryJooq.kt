package com.ridi.domain.videoshop.video.repository

import com.ridi.domain.videoshop.video.model.VideoPrice
import org.springframework.stereotype.Repository


@Repository
interface VideoPriceRepositoryJooq {
    fun getActivePriceByVideoId(videoId: Long): VideoPrice?
}
