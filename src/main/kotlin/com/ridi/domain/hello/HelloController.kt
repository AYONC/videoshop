package com.ridi.domain.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val helloService: HelloService
) {
    @GetMapping("/")
    fun hello(): String {
        val testName = helloService.getTestName()
        return "Hello, ${testName}"
    }

    @GetMapping("/test-async")
    fun testAsync(): String {
        helloService.testAsyncMethod()
        return "returned immediately"
    }
}