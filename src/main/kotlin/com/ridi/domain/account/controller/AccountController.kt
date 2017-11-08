package com.ridi.domain.account.controller

import com.ridi.domain.account.dto.AddAccountRequest
import com.ridi.domain.account.service.AccountService
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
    @RequestMapping("/login")
    fun account() = "account/login"

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
