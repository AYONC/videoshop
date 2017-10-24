package com.ridi.domain.example.dto

import com.ridi.domain.example.model.Task

data class TaskSummaryResponse(private val task: Task) {
    val id = task.id
    val title = task.title
    val member = task.member?.name
}