package com.ridi.domain.hello

import com.ridi.common.loggerFor
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class HelloService(
    @Value("\${testname}")
    private val testName: String
) {
    private val logger = loggerFor(javaClass)

    fun getTestName() = testName

    @Async
    fun testAsyncMethod() {
        Thread.sleep(3000)
        logger.debug("3 sec delayed")
    }
}