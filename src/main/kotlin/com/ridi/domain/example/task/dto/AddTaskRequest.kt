package com.ridi.domain.example.task.dto

import com.ridi.domain.example.task.model.Task
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class AddTaskRequest (
    @NotEmpty
    @Size(min = 1, max = 255)
    val title: String,
    @NotEmpty
    @Size(min = 1, max = 255)
    val content: String
) {
    fun toEntity() = Task(title = title, content = content)
}