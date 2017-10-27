package com.ridi.domain.task.controller

import com.ridi.domain.member.MemberService
import com.ridi.domain.task.dto.AddTaskRequest
import com.ridi.domain.task.service.TaskService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
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
        return "redirect:/"
    }

    @GetMapping("/{taskId}/")
    fun task(@PathVariable taskId: Long): Any {
        val task = taskService.getOne(taskId)
        val assignableMembers = memberService.findAssignableMembersByTask(task)

        return ModelAndView("task/task", mapOf(
            "task" to task,
            "assignableMembers" to assignableMembers
        ))
    }
}