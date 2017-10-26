package com.ridi.domain.task.repository

import com.ridi.domain.task.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: JpaRepository<Task, Long>, TaskRepositoryJooq
