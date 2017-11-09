package com.ridi.domain.home

import com.ridi.domain.member.service.MemberService
import com.ridi.domain.task.dto.TaskSummaryResponse
import com.ridi.domain.task.service.TaskService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpSession


@Controller
class HomeController(
    private val taskService: TaskService,
    private val memberService: MemberService
) {
    @RequestMapping("/")
    fun home(model: Model): String {
        return index()
    }

    @GetMapping("/index")
    fun index() = "home/index"

    @GetMapping("/register")
    fun register() = "account/register"

    @GetMapping("/forgot-password")
    fun forgotPassword() = "account/forgot-password"

    @RequestMapping("/overall")
    fun overall() = ModelAndView("home/overall", mapOf(
        "task_summaries" to taskService.findAll().map { TaskSummaryResponse(it) },
        "members" to memberService.findAll()
    ))

    @ResponseBody
    @RequestMapping(path = arrayOf("/session-test"), produces = arrayOf("text/plain"))
    fun sessionTest(session: HttpSession): String {
        session.setAttribute("test", "hello")
        return session.getAttribute("test") as String
    }
    // theme 나중에 필요 없어지면 삭제

    @GetMapping("/charts")
    fun charts() = "theme/charts"

    @GetMapping("/navbar")
    fun navbar() = "theme/navbar"

    @GetMapping("/blank")
    fun blank() = "theme/blank"

    @GetMapping("/tables")
    fun tables() = "theme/tables"
}
