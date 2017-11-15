package com.ridi.domain.videoshop.account.dto

import com.ridi.domain.videoshop.account.model.AccountRole

data class AddAccountRoleRequest(
    var roleId: Long,
    var accountId: Long
) {
    fun toEntity() = AccountRole(userId = accountId, roleId = roleId)
}
