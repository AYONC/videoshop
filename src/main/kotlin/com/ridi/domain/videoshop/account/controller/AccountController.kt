package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.dto.AddAccountRequest
import com.ridi.domain.videoshop.account.service.AccountService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid


@Controller
@RequestMapping("/account/")
class AccountController(
    private val accountService: AccountService
) {
    @GetMapping("/login")
    fun account() = "account/login"

    @PostMapping("/login")
    fun loginSuccess(): String {
        return "rediect:/"
    }

    @GetMapping("/add/")
    fun addAdminForm() = "admin/add_admin"

    @PostMapping("/add/")
    fun addAdmin(@Valid addAdminReq: AddAccountRequest): String {
        accountService.create(addAdminReq.toEntity())
        return "admin/add_admin_success"
    }

    @PostMapping("/update/")
    fun updateAdmin(@Valid addAdminReq: AddAccountRequest): String {
        accountService.create(addAdminReq.toEntity())
        return "admin/add_admin_success"
    }
}
