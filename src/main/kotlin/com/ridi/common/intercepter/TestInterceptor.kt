package com.ridi.common.intercepter

import com.ridi.common.loggerFor
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TestInterceptor : HandlerInterceptorAdapter() {
    private val logger = loggerFor(javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.debug("before handle request")
        return true
    }
}