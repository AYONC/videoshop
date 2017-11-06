package com.ridi.domain.video.service

import com.ridi.domain.video.dto.AddVideoPriceRequest
import com.ridi.domain.video.model.Video
import com.ridi.domain.video.model.VideoPrice
import com.ridi.domain.video.repository.VideoPriceRepository
import com.ridi.domain.video.repository.VideoRepository
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
