package com.ridi.domain.example.task

import javax.validation.constraints.NotEmpty

data class AddTaskRequest (
    @NotEmpty
    private val title: String,
    @NotEmpty
    private val content: String
) {
    fun toEntity() = Task(title = title, content = content)
}