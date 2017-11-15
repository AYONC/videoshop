package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.dto.AddAccountRoleRequest
import com.ridi.domain.videoshop.account.service.AccountRoleService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/accountRole/")
class AccountRoleController(
    private val accountRoleService: AccountRoleService
) {
    @PostMapping("/add/")
    fun create(@Valid addAccountRoleRequest: AddAccountRoleRequest) {
        val accountRole = addAccountRoleRequest.toEntity()
        accountRoleService.create(accountRole)
    }
}