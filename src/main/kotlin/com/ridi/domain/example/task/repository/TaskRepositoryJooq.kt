package com.ridi.domain.example.task.repository

import com.ridi.domain.example.task.model.Task
import org.springframework.stereotype.Repository

@Repository
interface TaskRepositoryJooq {
    fun findAssigned(assignedMemberId: Long): List<Task>
}
