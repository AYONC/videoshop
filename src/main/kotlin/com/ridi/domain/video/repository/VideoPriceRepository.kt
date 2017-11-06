package com.ridi.domain.video.repository

import com.ridi.domain.video.model.Video
import com.ridi.domain.video.model.VideoPrice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface VideoPriceRepository: JpaRepository<VideoPrice, Long>, VideoPriceRepositoryJooq {
    fun findByVideo(video: Video): List<VideoPrice>
}
