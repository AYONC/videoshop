package com.ridi.domain.example.dto

import com.ridi.domain.example.model.Task
import javax.validation.constraints.NotEmpty

data class AddTaskRequest (
    @NotEmpty
    private val title: String,
    @NotEmpty
    private val content: String
) {
    fun toEntity() = Task(title = title, content = content)
}