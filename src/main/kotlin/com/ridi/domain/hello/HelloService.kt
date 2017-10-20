package com.ridi.domain.hello

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class HelloService(
    @Value("\${testname}")
    private val testName: String
) {
    fun getTestName() = testName
}