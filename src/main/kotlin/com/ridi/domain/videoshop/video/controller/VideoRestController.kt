package com.ridi.domain.videoshop.video.controller

import com.ridi.domain.videoshop.video.dto.AddVideoPriceRequest
import com.ridi.domain.videoshop.video.service.VideoPriceService
import com.ridi.domain.videoshop.video.service.VideoService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


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
