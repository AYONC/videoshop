package com.ridi.domain.videoshop.video.repository

import com.ridi.domain.videoshop.video.model.Video
import com.ridi.domain.videoshop.video.model.VideoPrice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface VideoPriceRepository: JpaRepository<VideoPrice, Long>, VideoPriceRepositoryJooq {
    fun findByVideo(video: Video): List<VideoPrice>
}
