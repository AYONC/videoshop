package com.ridi.domain.videoshop.video.controller

import com.ridi.domain.videoshop.video.dto.AddVideoPriceRequest
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
@RequestMapping("/videos/{videoId}/prices/")
class VideoPriceController(
        private val videoService: VideoService,
        private val videoPriceService: VideoPriceService
) {

    @GetMapping
    fun videoPriceList(@PathVariable videoId: Long): Any {
        val video = videoService.getOne(videoId)
        val videoPrices = videoPriceService.findByVideo(video)
        return ModelAndView(
            "video/price_list", mapOf(
                "videoId" to videoId,
                "video" to video,
                "videoPrices" to videoPrices
            )
        )
    }

    @GetMapping("/add/")
    fun addPriceForm(@PathVariable videoId: Long) = "video/add_price"

    @PostMapping("/add/")
    fun addPrice(@PathVariable videoId: Long, @Valid addVideoPriceRequest: AddVideoPriceRequest): Any {
        val video = videoService.getOne(videoId)
        val videoPrice = addVideoPriceRequest.toEntity()
        videoPriceService.insert(video, videoPrice)
        return "redirect:/videos/$videoId/prices/"
    }
}
