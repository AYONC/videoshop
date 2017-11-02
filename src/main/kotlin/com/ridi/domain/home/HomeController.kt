package com.ridi.domain.home

import com.ridi.domain.member.service.MemberService
import com.ridi.domain.task.service.TaskService
import com.ridi.domain.task.dto.TaskSummaryResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController(
        private val taskService: TaskService,
        private val memberService: MemberService
) {
    @GetMapping("/")
    fun overall() = ModelAndView("overall", mapOf(
        "task_summaries" to taskService.findAll().map { TaskSummaryResponse(it) },
        "members" to memberService.findAll()
    ))
}