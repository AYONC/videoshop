package com.ridi.domain.video.service

import com.ridi.domain.video.repository.VideoRepository
import org.springframework.stereotype.Service


@Service
class VideoService(
    private val videoRepo: VideoRepository
)
