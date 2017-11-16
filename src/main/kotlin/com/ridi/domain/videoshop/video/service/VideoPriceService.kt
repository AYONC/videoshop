package com.ridi.domain.videoshop.video.service

import com.ridi.domain.videoshop.video.model.Video
import com.ridi.domain.videoshop.video.model.VideoPrice
import com.ridi.domain.videoshop.video.repository.VideoPriceRepository
import com.ridi.domain.videoshop.video.repository.VideoRepository
import org.springframework.stereotype.Service


@Service
class VideoPriceService(
        private val videoRepo: VideoRepository,
        private val videoPriceRepo: VideoPriceRepository
) {
    fun insert(video: Video, videoPrice: VideoPrice) {
        videoPrice.video = video
        videoPriceRepo.save(videoPrice)
    }

    fun findByVideo(video: Video) = videoPriceRepo.findByVideo(video)
}
