package com.ridi.domain.video.controller

import com.ridi.domain.video.service.VideoService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/videos/")
class VideoController(
    private val videoService: VideoService
) {}
