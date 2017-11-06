package com.ridi.domain.video.controller

import com.ridi.domain.video.dto.AddVideoRequest
import com.ridi.domain.video.service.VideoService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid


@Controller
@RequestMapping("/videos/")
class VideoController(
    private val videoService: VideoService
) {
    @GetMapping("/")
    fun list(): Any {
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
}
