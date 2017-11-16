package com.ridi.domain.videoshop.video.repository

import com.ridi.domain.videoshop.video.model.Video
import com.ridi.domain.videoshop.video.model.VideoPrice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface VideoPriceRepository: JpaRepository<VideoPrice, Long>, VideoPriceRepositoryJooq {
    fun findByVideoOrderByIdDesc(video: Video): List<VideoPrice>

    @Modifying
    @Query("UPDATE VideoPrice SET is_active=0 WHERE is_active=1 AND video_id = :videoId")
    fun inactivePriceByVideoId(@Param("videoId") videoId: Long)

    @Modifying
    @Query("UPDATE VideoPrice SET is_active=1 WHERE video_id = :videoId AND id = :priceId")
    fun activeByVideoIdAndPriceId(@Param("videoId") videoId: Long, @Param("priceId") priceId: Long)
}
