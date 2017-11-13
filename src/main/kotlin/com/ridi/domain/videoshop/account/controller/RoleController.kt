package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.model.AccountRole
import com.ridi.domain.videoshop.account.service.AccountRoleService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/accountRole/")
class AccountRoleController(
    private val accountRoleService: AccountRoleService
) {
    @GetMapping("/{userId}/")
    fun getAccountRoles(@PathVariable userId: Long): Collection<AccountRole>
        = accountRoleService.findByUserId(userId)
}