package com.ridi.common.security


import org.springframework.security.web.authentication.WebAuthenticationDetails
import javax.servlet.http.HttpServletRequest

class AuthenticationDetails(request: HttpServletRequest) : WebAuthenticationDetails(request) {

    val verificationCode: String

    init {
        verificationCode = request.getParameter("code")
    }

    companion object {

        private val serialVersionUID = 1L
    }
}