package com.ridi.domain.home

import com.ridi.domain.member.MemberService
import com.ridi.domain.task.service.TaskService
import com.ridi.domain.task.dto.TaskSummaryResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpSession
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody



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

    @ResponseBody
    @RequestMapping(path = arrayOf("/session-test"), produces = arrayOf("text/plain"))
    fun sessionTest(session: HttpSession): String {
        session.setAttribute("test", "hello")
        return session.getAttribute("test") as String
    }
}