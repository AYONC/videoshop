package com.ridi.domain.videoshop.video.controller

import com.ridi.domain.videoshop.video.dto.AddVideoRequest
import com.ridi.domain.videoshop.video.dto.UpdateVideoRequest
import com.ridi.domain.videoshop.video.service.VideoPriceService
import com.ridi.domain.videoshop.video.service.VideoService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
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
            "video/video_list", mapOf(
            "videos" to videos
        ))
    }

    @GetMapping("/add/")
    fun addVideoForm() = "video/add_video"

    @PostMapping("/add/")
    fun addVideo(@Valid addVideoRequest: AddVideoRequest): String {
        videoService.create(addVideoRequest.toEntity())
        return "redirect:/videos/"
    }

    @GetMapping("/{videoId}/")
    fun video(@PathVariable videoId: Long): Any {
        val video = videoService.getOne(videoId)
        val activePrice = videoPriceService.getActivePriceByVideo(video)
        return ModelAndView(
            "video/video_detail", mapOf(
            "video" to video,
            "activePrice" to activePrice
        ))
    }

    @PostMapping("/{videoId}/")
    fun updateVideo(@PathVariable videoId: Long, @Valid updateVideoRequest: UpdateVideoRequest): String {
        videoService.update(videoId, updateVideoRequest)
        return "redirect:/videos/$videoId/"
    }
}
