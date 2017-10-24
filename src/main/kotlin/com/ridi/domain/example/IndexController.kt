package com.ridi.domain.example

import com.ridi.domain.example.member.AddMemberRequest
import com.ridi.domain.example.task.AddTaskRequest
import com.ridi.domain.example.task.TaskSummaryResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import com.ridi.domain.example.task.TaskService
import com.ridi.domain.example.member.MemberService
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
@RequestMapping("/example")
class IndexController(
        private val taskService: TaskService,
        private val memberService: MemberService
) {
    @GetMapping("/")
    fun overall() = ModelAndView("example/overall", mapOf(
        "task_summaries" to taskService.findAll().map { TaskSummaryResponse(it) },
        "members" to memberService.findAll()
    ))

    @GetMapping("/add-member/")
    fun addMemberForm() = "example/add_member"

    @PostMapping("/add-member/")
    fun addMember(@Valid addMemberReq: AddMemberRequest): String {
        memberService.create(addMemberReq.toEntity())
        return "redirect:/example/"
    }

    @GetMapping("/add-task/")
    fun addTaskForm() = "example/add_task"

    @PostMapping("/add-task/")
    fun addTask(@Valid addTaskReq: AddTaskRequest): String {
        taskService.create(addTaskReq.toEntity())
        return "redirect:/example/"
    }
}