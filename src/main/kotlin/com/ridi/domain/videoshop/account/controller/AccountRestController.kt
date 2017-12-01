package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.service.CustomerService
import com.ridi.domain.videoshop.account.service.StaffService
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/account")
class AccountRestController(
    private val staffService: StaffService,
    private val customerService: CustomerService,
    private val passwordEncoder: PasswordEncoder
) {
    @PostMapping(value = "/staff/register/mfa", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseBody
    fun enableMFA(principal: Principal): Account {
        val authToken = principal as UsernamePasswordAuthenticationToken
        val user = authToken.principal as Account
        user.isUsing2FA = true
        staffService.updateAccount(user)
        return user
    }

    // 로그인한 유저 확인용
    @PostMapping(value = "/loginedUser", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseBody
    fun getUser(@AuthenticationPrincipal user: Account): Account {
        return user
    }
}