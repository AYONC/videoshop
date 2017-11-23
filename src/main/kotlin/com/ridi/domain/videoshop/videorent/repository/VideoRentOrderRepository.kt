package com.ridi.domain.videoshop.videorent.repository

import com.ridi.domain.videoshop.videorent.model.VideoRent
import com.ridi.domain.videoshop.videorent.model.VideoRentOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRentOrderRepository : JpaRepository<VideoRentOrder, Long>