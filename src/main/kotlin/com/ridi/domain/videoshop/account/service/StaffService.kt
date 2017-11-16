package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.AccountRole
import com.ridi.domain.videoshop.account.repository.AccountRepository
import com.ridi.domain.videoshop.account.repository.AccountRoleRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class StaffService(
    val privilegeService: PrivilegeService,
    val accountRepo: AccountRepository,
    val accountRoleRepo: AccountRoleRepository
) {
    @Transactional
    fun createAsStaff(account: Account) {
        accountRepo.save(account)

        val staffPrivilege = privilegeService.getStaffPrivilege()
        val accountRole = AccountRole(userId = account.id, roleId = staffPrivilege.id)
        accountRoleRepo.save(accountRole)
    }
}
