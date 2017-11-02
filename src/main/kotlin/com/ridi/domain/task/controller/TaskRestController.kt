package com.ridi.domain.task.controller

import com.ridi.domain.member.service.MemberService
import com.ridi.domain.task.service.TaskService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks/")
class TaskRestController(
        private val taskService: TaskService,
        private val memberService: MemberService
) {
    @PutMapping("/{taskId}/assign/{memberId}/")
    fun assign(@PathVariable("taskId") taskId: Long, @PathVariable("memberId") memberId: Long): Any {
        val task = taskService.getOne(taskId)
        val member = memberService.getOne(memberId)
        taskService.assign(task, member)
        return mapOf("success" to true)
    }

    @PutMapping("/{taskId}/cancel-assign/")
    fun cancelAssign(@PathVariable taskId: Long): Any {
        taskService.cancelAssign(taskService.getOne(taskId))
        return mapOf("success" to true)
    }
}