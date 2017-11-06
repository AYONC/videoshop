package com.ridi.domain.video.controller

import com.ridi.domain.video.dto.AddVideoPriceRequest
import com.ridi.domain.video.service.VideoPriceService
import com.ridi.domain.video.service.VideoService
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

    @PostMapping("{videoId}/prices/add/")
    fun addPrice(@PathVariable videoId: Long, @Valid addVideoPriceRequest: AddVideoPriceRequest): Any {
        val video = videoService.getOne(videoId)
        val videoPrice = addVideoPriceRequest.toEntity()
        videoPriceService.insert(video, videoPrice)
        return mapOf("success" to true)
    }
}
