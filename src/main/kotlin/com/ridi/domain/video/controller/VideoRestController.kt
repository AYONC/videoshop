package com.ridi.domain.video.controller

import com.ridi.domain.video.service.VideoService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/videos/")
class VideoRestController(
    private val videoService: VideoService
) {}
