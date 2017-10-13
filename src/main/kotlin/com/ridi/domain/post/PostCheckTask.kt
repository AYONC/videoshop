package com.ridi.domain.post

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class PostCheckTask {
    @Scheduled(fixedRate = 5000)
    fun checkPost() {
        println("check post task")
    }
}
