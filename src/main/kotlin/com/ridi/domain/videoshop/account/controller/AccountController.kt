package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.dto.AddAccountRequest
import com.ridi.domain.videoshop.account.service.CustomerService
import com.ridi.domain.videoshop.account.service.StaffService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@Controller
@RequestMapping("/account/")
class AccountController(
    private val staffService: StaffService,
    private val customerService: CustomerService,
    private val passwordEncoder: PasswordEncoder
) {
    @GetMapping("/login")
    fun account() = "account/login"

    @PostMapping("/login")
    fun loginSuccess(): String {
        return "redirect:/index"
    }

    @GetMapping("/register-staff")
    fun registerStaff() = "account/register-staff"

    @PostMapping("/register-staff")
    fun registerStaff(@Valid addAccountReq: AddAccountRequest): String {
        staffService.createAsStaff(addAccountReq.toEntity(passwordEncoder))
        return "admin/add_admin_success"
    }

    @GetMapping("/register-customer")
    fun registerCustomer() = "account/register-customer"

    @PostMapping("/register-customer")
    fun registerCustomer(@Valid addAccountReq: AddAccountRequest): String {
        customerService.createAsCustomer(addAccountReq.toEntity(passwordEncoder))
        return "admin/add_admin_success"
    }

    @GetMapping("/forgot-password")
    fun forgotPassword() = "account/forgot-password"
}
