package com.ridi.domain.example.service

import com.ridi.domain.example.model.Member
import com.ridi.domain.example.model.Task
import org.springframework.stereotype.Service
import com.ridi.domain.example.repository.TaskRepository

@Service
class TaskService(
    private val taskRepo: TaskRepository
) {
    fun create(task: Task) = taskRepo.save(task)

    fun assign(task: Task, member: Member) {
        task.assign(member)
        taskRepo.save(task)
    }

    fun cancelAssign(task: Task) {
        task.cancelAssign()
        taskRepo.save(task)
    }

    fun findAll() = taskRepo.findAll()

    fun getOne(id: Long) = taskRepo.getOne(id)
}
