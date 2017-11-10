package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.dto.LoginRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/login")
class LoginRestController {
    @GetMapping("/")
    fun loginPage(@Valid loginRequest: LoginRequest) = "account/login"

    @PostMapping("/")
    fun login(@Valid loginRequest: LoginRequest) = "account/login"
}