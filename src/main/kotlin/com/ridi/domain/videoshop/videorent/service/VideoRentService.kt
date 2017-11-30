package com.ridi.domain.videoshop.videorent.service

import com.ridi.domain.videoshop.account.exception.CustomerNotRegisterBirthException
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.video.model.Video
import com.ridi.domain.videoshop.video.service.VideoService
import com.ridi.domain.videoshop.videorent.dto.RentVideoRequest
import com.ridi.domain.videoshop.videorent.exception.VideoHasRentedBeforeException
import com.ridi.domain.videoshop.videorent.model.VideoRent
import com.ridi.domain.videoshop.videorent.repository.VideoRentRepository
import org.springframework.stereotype.Service

@Service
class VideoRentService(
    val videoService: VideoService,
    val videoRentOrderService: VideoRentOrderService,
    val videoRentRepo: VideoRentRepository
) {
    fun rent(req: RentVideoRequest, account: Account): VideoRent {
        account.assertIsCustomer()

        val video = videoService.getOne(req.videoId)
        if (!req.isIgnoreRentBefore) {
            assertIsVideoNotRentedFor(video, account)
        }

        assertIsVideoRentable(video, account)
        assertIsPassAgeRating(video, account)

        val order = videoRentOrderService.createOrder(req, video)
        return order.provideFor(account)
    }

    fun findByVideoAndAccount(video: Video, account: Account) = videoRentRepo.findByVideoIdAndAccountId(video.id, account.id)

    private fun assertIsVideoNotRentedFor(video: Video, account: Account) {
        if (findByVideoAndAccount(video, account).count() > 0) {
            throw VideoHasRentedBeforeException()
        }
    }

    private fun assertIsVideoRentable(video: Video, account: Account) {
        video.assertIsOpened()
    }

    private fun assertIsPassAgeRating(video: Video, account: Account) {
        val age = account.getAge()
        video.assertIsPassing(age)
    }
}
