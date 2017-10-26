package com.ridi.domain.task.repository

import com.ridi.domain.task.model.Task
import org.springframework.stereotype.Repository

@Repository
interface TaskRepositoryJooq {
    fun findAssigned(assignedMemberId: Long): List<Task>
}
