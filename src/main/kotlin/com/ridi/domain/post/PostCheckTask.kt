package com.ridi.domain.post

import com.ridi.common.loggerFor
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class PostCheckTask {
    private val logger = loggerFor(javaClass)

    @Scheduled(fixedRate = 50000)
    fun checkPost() {
        logger.debug("check post task")
    }
}
