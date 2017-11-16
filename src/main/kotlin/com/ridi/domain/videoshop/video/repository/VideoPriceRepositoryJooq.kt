package com.ridi.domain.videoshop.video.repository

import org.springframework.stereotype.Repository
import com.ridi.domain.videoshop.video.model.VideoPrice


@Repository
interface VideoPriceRepositoryJooq {
    fun getActivePriceByVideoId(videoId: Long): VideoPrice?
}
