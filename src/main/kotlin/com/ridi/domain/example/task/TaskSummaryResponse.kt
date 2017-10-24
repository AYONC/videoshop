package com.ridi.domain.example.task

data class TaskSummaryResponse(private val task: Task) {
    val id = task.id
    val title = task.title
    val member = task.member?.name
}