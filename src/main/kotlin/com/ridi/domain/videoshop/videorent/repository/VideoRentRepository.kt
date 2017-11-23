package com.ridi.domain.videoshop.videorent.repository

import com.ridi.domain.videoshop.videorent.model.VideoRent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRentRepository : JpaRepository<VideoRent, Long> {
    fun findByVideoIdAndAccountId(videoId: Long, accountId: Long): List<VideoRent>
}
