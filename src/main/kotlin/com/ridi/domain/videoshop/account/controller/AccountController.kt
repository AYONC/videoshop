package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.dto.AddCustomerRequest
import com.ridi.domain.videoshop.account.dto.AddStaffRequest
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.service.CustomerService
import com.ridi.domain.videoshop.account.service.StaffService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import java.io.UnsupportedEncodingException
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@Controller
@RequestMapping("/account/")
class AccountController(
    private val staffService: StaffService,
    private val customerService: CustomerService,
    private val passwordEncoder: PasswordEncoder
) {
    @GetMapping("/login")
    fun getLogin(request: HttpServletRequest, response: HttpServletResponse) = "account/login"

    @PostMapping("/login")
    fun postLogin(): String {
        return "account/login"
    }

    @GetMapping("/staff/register")
    fun registerStaff() = "account/register-staff"

    @PostMapping("/staff/register")
    fun registerStaff(@Valid addStaffReq: AddStaffRequest): String {
        staffService.createAsStaff(addStaffReq.toEntity(passwordEncoder))
        return "redirect:/staff/register/mfa"
    }


    @RequestMapping(value = "/staff/register/mfa", method = arrayOf(RequestMethod.GET))
    @Throws(UnsupportedEncodingException::class)
    fun confirmRegistration(principal: Principal): ModelAndView {
        val authToken = principal as UsernamePasswordAuthenticationToken
        val user = authToken.principal as User
        val account: Account = staffService.accountRepo.findByUsername(user.username)?.get(0) ?: throw NullPointerException("Invalid username or password")
        return ModelAndView(
            "account/qrcode", mapOf(
            "qr" to staffService.generateQRUrl(account)
        ))
    }

    @GetMapping("/customer/register")
    fun registerCustomer() = "account/register-customer"

    @PostMapping("/customer/register")
    fun registerCustomer(@Valid addCustomerReq: AddCustomerRequest): String {
        customerService.createAsCustomer(addCustomerReq.toEntity(passwordEncoder))
        return "admin/add_admin_success"
    }

    @GetMapping("/forgot-password")
    fun forgotPassword() = "account/forgot-password"
}
