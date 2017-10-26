package com.ridi.domain.task

import com.ridi.domain.member.AddMemberRequest
import com.ridi.domain.member.MemberService
import com.ridi.domain.task.service.TaskService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

@Controller
@RequestMapping("/members/")
class MemberController (
        private val taskService: TaskService,
        private val memberService: MemberService
) {
    @GetMapping("/add/")
    fun addMemberForm() = "add_member"

    @PostMapping("/add/")
    fun addMember(@Valid addMemberReq: AddMemberRequest): String {
        memberService.create(addMemberReq.toEntity())
        return "redirect:/"
    }

    @GetMapping("/{memberId}/")
    fun member(@PathVariable memberId: Long): Any {
        val member = memberService.getOne(memberId)
        val assignedTasks = taskService.findAssigned(member)

        return ModelAndView("member/member", mapOf(
            "member" to member,
            "assignedTasks" to assignedTasks
        ))
    }
}