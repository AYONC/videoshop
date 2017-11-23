package com.ridi.domain.videoshop.videorent.service

import com.ridi.domain.videoshop.video.model.Video
import com.ridi.domain.videoshop.videorent.dto.RentVideoRequest
import com.ridi.domain.videoshop.videorent.model.VideoRentOrder
import com.ridi.domain.videoshop.videorent.repository.VideoRentOrderRepository
import org.springframework.stereotype.Service

@Service
class VideoRentOrderService(
    val videoRentOrderRepo: VideoRentOrderRepository
) {
    fun createOrder(req: RentVideoRequest, video: Video): VideoRentOrder {
        val order = VideoRentOrder(
            video = video,
            rentDays = req.rentDays
        )
        videoRentOrderRepo.save(order)

        return order
    }
}
