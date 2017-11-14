package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.dto.AddAccountRequest
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.service.AccountService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid


@Controller
@RequestMapping("/account/")
class AccountController(
    private val accountService: AccountService,
    private val passwordEncoder: PasswordEncoder
) {
    @GetMapping("/login")
    fun account() = "account/login"

    @PostMapping("/login")
    fun loginSuccess(): String {
        return "rediect:/index"
    }

    @GetMapping("/register")
    fun addAdminForm() = "account/register"

    @PostMapping("/register")
    fun addAdmin(@Valid addAdminReq: AddAccountRequest): String {
        var account: Account = Account(
            name = addAdminReq.name,
            username = addAdminReq.username,
            password = passwordEncoder.encode(addAdminReq.password),
            phone = addAdminReq.phone
        )

        accountService.create(account)

        return "admin/add_admin_success"
    }

    @GetMapping("/forgot-password")
    fun forgotPassword() = "account/forgot-password"

}
