package com.ridi.domain.video.service

import com.ridi.domain.video.dto.UpdateVideoRequest
import com.ridi.domain.video.model.Video
import com.ridi.domain.video.repository.VideoRepository
import org.springframework.stereotype.Service


@Service
class VideoService(
    private val videoRepo: VideoRepository
) {
    fun create(video: Video) {
        videoRepo.save(video)
    }

    fun findAll() = videoRepo.findAll()

    fun getOne(videoId: Long) = videoRepo.getOne(videoId)

    fun update(videoId: Long, data: UpdateVideoRequest): Video {
        val video: Video = getOne(videoId)
        video.title = data.title
        video.description = data.description
        videoRepo.save(video)
        return video
    }
}
