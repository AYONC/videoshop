package com.ridi.domain.task.dto

import com.ridi.domain.task.model.Task

data class TaskSummaryResponse(private val task: Task) {
    val id = task.id
    val title = task.title
    val member = task.member?.name
}