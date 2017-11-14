package com.ridi.domain.videoshop.video.repository

import com.ridi.domain.videoshop.video.model.Video
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface VideoRepository: JpaRepository<Video, Long>, VideoRepositoryJooq
