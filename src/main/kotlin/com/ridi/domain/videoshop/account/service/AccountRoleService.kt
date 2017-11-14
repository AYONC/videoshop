package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.model.AccountRole
import com.ridi.domain.videoshop.account.repository.AccountRoleRepository
import org.springframework.stereotype.Service

@Service
class AccountRoleService(
    private var accountRoleRepo: AccountRoleRepository
) {
    fun create(accountRole: AccountRole) {
        accountRoleRepo.save(accountRole)
    }

    fun findByUserId(userId: Long): Collection<AccountRole> = accountRoleRepo.findByUserId(userId = userId)
}
