package com.ridi.domain.example.task.repository

import com.ridi.domain.example.task.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: JpaRepository<Task, Long>, TaskRepositoryJooq {
    fun findByTitle(title: String): List<Task>
}
