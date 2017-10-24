package com.ridi.domain.example.controller

import com.ridi.domain.example.service.MemberService
import org.springframework.stereotype.Controller
import com.ridi.domain.example.service.TaskService
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/example/tasks")
class TaskController (
    private val taskService: TaskService,
    private val memberService: MemberService
) {
    @GetMapping("/{taskId}/")
    fun task(@PathVariable taskId: Long): Any {
        val task = taskService.getOne(taskId)
        val assignableMembers = memberService.findAssignableMembersByTask(task)

        return ModelAndView("example/task/task", mapOf(
            "task" to task,
            "assignableMembers" to assignableMembers
        ))
    }

    @PutMapping("/{taskId}/assign/{memberId}/")
    @ResponseBody
    fun assign(@PathVariable("taskId") taskId: Long, @PathVariable("memberId") memberId: Long): Any {
        val task = taskService.getOne(taskId)
        val member = memberService.getOne(memberId)
        taskService.assign(task, member)
        return mapOf("success" to true)
    }

    @PutMapping("/{taskId}/cancel-assign/")
    @ResponseBody
    fun cancelAssign(@PathVariable taskId: Long): Any {
        taskService.cancelAssign(taskService.getOne(taskId))
        return mapOf("success" to true)
    }
}