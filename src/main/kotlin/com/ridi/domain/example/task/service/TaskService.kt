package com.ridi.domain.example.task.service

import com.ridi.domain.example.member.model.Member
import com.ridi.domain.example.task.model.Task
import com.ridi.domain.example.task.repository.TaskRepository
import org.springframework.stereotype.Service

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

    fun findAssigned(member: Member) = taskRepo.findAssigned(member.id)

    fun findByTitle(title: String) = taskRepo.findByTitle(title)

    fun getOne(id: Long) = taskRepo.getOne(id)
}
