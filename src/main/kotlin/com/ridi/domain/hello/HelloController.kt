package com.ridi.domain.hello

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val helloService: HelloService
) {
    @RequestMapping("/")
    fun hello(): String {
        val testName = helloService.getTestName()
        return "Hello, ${testName}"
    }
}