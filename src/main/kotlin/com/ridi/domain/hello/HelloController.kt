package com.ridi.domain.hello

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

@Controller
class HelloController(
    private val helloService: HelloService
) {
    @GetMapping("/")
    fun index() = ModelAndView("index", mapOf(
        "name" to "jaeyo"
    ))

    @GetMapping("/name")
    @ResponseBody
    fun name() = mapOf(
        "msg" to "Hello, ${helloService.getTestName()}"
    )

    @GetMapping("/test-async")
    @ResponseBody
    fun testAsync(): String {
        helloService.testAsyncMethod()
        return "returned immediately"
    }
}