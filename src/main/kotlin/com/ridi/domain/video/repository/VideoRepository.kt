package com.ridi.domain.video.repository

import com.ridi.domain.video.model.Video
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface VideoRepository: JpaRepository<Video, Long>, VideoRepositoryJooq
