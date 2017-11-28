package com.ridi.common.security


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.springframework.web.servlet.LocaleResolver
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFailureHandler : SimpleUrlAuthenticationFailureHandler() {

    @Autowired
    private val messages: MessageSource? = null

    @Autowired
    private val localeResolver: LocaleResolver? = null

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException) {
        setDefaultFailureUrl("/account/login")

        request.setAttribute("error", exception.message)
        request.getRequestDispatcher("/account/login").forward(request, response)
    }
}