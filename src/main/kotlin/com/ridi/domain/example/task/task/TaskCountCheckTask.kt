package com.ridi.domain.example.task.task

import com.ridi.common.loggerFor
import com.ridi.domain.example.task.service.TaskService
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@Profile("test")
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