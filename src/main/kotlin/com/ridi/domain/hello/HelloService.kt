package com.ridi.domain.hello

import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class HelloService(
    @Value("\${testname}")
    private val testName: String
) {
    fun getTestName() = testName

    @Async
    fun testAsyncMethod() {
        Thread.sleep(3000)
        println("3 sec delayed")
    }
}