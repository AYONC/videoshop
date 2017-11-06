package com.ridi.domain.video.controller

import com.ridi.domain.video.dto.AddVideoRequest
import com.ridi.domain.video.dto.UpdateVideoRequest
import com.ridi.domain.video.service.VideoPriceService
import com.ridi.domain.video.service.VideoService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid


@Controller
@RequestMapping("/videos/")
class VideoController(
    private val videoService: VideoService,
    private val videoPriceService: VideoPriceService
) {
    @GetMapping("/")
    fun videoList(): Any {
        val videos = videoService.findAll()
        return ModelAndView(
            "video/list", mapOf(
            "videos" to videos
        ))
    }

    @GetMapping("/add/")
    fun addVideoForm() = "video/add_video"

    @PostMapping("/add/")
    fun addVideo(@Valid addVideoRequest: AddVideoRequest): String {
        videoService.create(addVideoRequest.toEntity())
        return "video/add_video_success"
    }

    @GetMapping("/{videoId}/")
    fun video(@PathVariable videoId: Long): Any {
        val video = videoService.getOne(videoId)
        return ModelAndView(
                "video/detail", mapOf(
                "video" to video
        ))
    }

    @PostMapping("/{videoId}/")
    fun updateVideo(@PathVariable videoId: Long, @Valid updateVideoRequest: UpdateVideoRequest): Any {
        val video = videoService.update(videoId, updateVideoRequest)
        return  ModelAndView(
                "video/detail", mapOf(
                "video" to video
        ))
    }
}
