package com.ridi.domain.task.controller

import com.ridi.domain.example.member.MemberService
import com.ridi.domain.task.dto.AddTaskRequest
import com.ridi.domain.task.service.TaskService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

@Controller
@RequestMapping("/tasks/")
class TaskController (
        private val taskService: TaskService,
        private val memberService: MemberService
) {
    @GetMapping("/add/")
    fun addTaskForm() = "add_task"

    @PostMapping("/add/")
    fun addTask(@Valid addTaskReq: AddTaskRequest): String {
        taskService.create(addTaskReq.toEntity())
        return "redirect:/example/"
    }

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