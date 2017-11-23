package com.ridi.domain.videoshop.video.controller

import com.ridi.domain.videoshop.video.service.VideoPriceService
import com.ridi.domain.videoshop.video.service.VideoService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/videos/")
class VideoRestController(
        private val videoService: VideoService,
        private val videoPriceService: VideoPriceService
) {
    @PutMapping("/{videoId}/open/")
    fun openVideo(@PathVariable videoId: Long): Any {
        videoService.open(videoId)
        return mapOf("success" to true)
    }

    @PutMapping("/{videoId}/close/")
    fun closeVideo(@PathVariable videoId: Long): Any {
        videoService.close(videoId)
        return mapOf("success" to true)
    }
}
