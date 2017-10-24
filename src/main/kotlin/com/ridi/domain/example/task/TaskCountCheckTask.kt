package com.ridi.domain.example.task

import com.ridi.common.loggerFor
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TaskCountCheckTask(
    private val taskService: TaskService
) {
    private val logger = loggerFor(javaClass)
    private var lastCount = -1

    @Scheduled(fixedRate = 5000)
    fun checkTaskCount() {
        val count = taskService.findAll().size
        if (count != lastCount) {
            lastCount = count
            logger.debug("current task count: ${count}")
        }
    }
}