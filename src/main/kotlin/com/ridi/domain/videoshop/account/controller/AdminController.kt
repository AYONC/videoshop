package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.dto.AddAccountRequest
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/admin/")
class AdminController() {
    @Secured("ADMIN")
    @GetMapping("/")
    fun admin() = "admin/add_admin"

    @PostMapping("/update/")
    fun updateAdmin(@Valid addAdminReq: AddAccountRequest): String {
//        accountService.create(addAdminReq.toEntity())
        return "admin/add_admin_success"
    }
}