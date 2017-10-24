package com.ridi.domain.example.task

import org.springframework.stereotype.Repository

@Repository
interface TaskRepositoryJooq {
    fun findAssigned(assignedMemberId: Long): List<Task>
}
