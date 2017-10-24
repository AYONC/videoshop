package com.ridi.domain.example.task

import com.ridi.domain.example.member.MemberService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/example/members")
class MemberController (
        private val taskService: TaskService,
        private val memberService: MemberService
) {
    @GetMapping("/{memberId}/")
    fun task(@PathVariable memberId: Long): Any {
        val member = memberService.getOne(memberId)
        val assignedTasks = taskService.findAssigned(member)

        return ModelAndView("example/member/member", mapOf(
            "member" to member,
            "assignedTasks" to assignedTasks
        ))
    }
}