package com.ridi.domain.video.service

import com.ridi.domain.video.model.Video
import com.ridi.domain.video.repository.VideoPriceRepository
import com.ridi.domain.video.repository.VideoRepository
import org.springframework.stereotype.Service


@Service
class VideoPriceService(
    private val videoRepo: VideoRepository,
    private val videoPriceRepo: VideoPriceRepository
) {
    fun findByVideo(video: Video) = videoPriceRepo.findByVideo(video)
}
