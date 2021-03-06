package com.ridi.domain.videoshop.video.service

import com.ridi.domain.videoshop.coin.exception.VideoPriceNotExistException
import com.ridi.domain.videoshop.video.model.Video
import com.ridi.domain.videoshop.video.model.VideoPrice
import com.ridi.domain.videoshop.video.repository.VideoPriceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class VideoPriceService(
    private val videoPriceRepo: VideoPriceRepository
) {
    fun insert(video: Video, videoPrice: VideoPrice) {
        videoPrice.video = video
        videoPriceRepo.save(videoPrice)
    }

    fun getLatestByVideo(video: Video): VideoPrice {
        val prices = videoPriceRepo.findByVideoOrderByIdDesc(video)
        if (prices.count() == 0) {
            throw VideoPriceNotExistException()
        }

        return prices.get(0)
    }

    fun findByVideo(video: Video) = videoPriceRepo.findByVideoOrderByIdDesc(video)

    fun getActivePriceByVideo(video: Video) = videoPriceRepo.getActivePriceByVideoId(video.id)

    @Transactional
    fun active(videoId: Long, priceId: Long) {
        videoPriceRepo.inactivePriceByVideoId(videoId)
        videoPriceRepo.activeByVideoIdAndPriceId(videoId, priceId)
    }
}
