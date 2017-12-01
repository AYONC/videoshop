package com.ridi.domain.videoshop.video.controller

import com.ridi.domain.videoshop.video.service.VideoPriceService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/videos/{videoId}/prices/")
class VideoPriceRestController(
        private val videoPriceService: VideoPriceService
) {
    @PutMapping("/{priceId}/active/")
    fun openVideo(
        @PathVariable("videoId") videoId: Long,
        @PathVariable("priceId") priceId: Long
    ): Any {
        videoPriceService.active(videoId, priceId)
        return mapOf("success" to true)
    }
}