package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.AccountRole
import com.ridi.domain.videoshop.account.repository.AccountRepository
import com.ridi.domain.videoshop.account.repository.AccountRoleRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CustomerService(
    val privilegeService: PrivilegeService,
    val accountRepo: AccountRepository,
    val accountRoleRepo: AccountRoleRepository
) {
    @Transactional
    fun createAsCustomer(account: Account) {
        accountRepo.save(account)

        val customerPrivilege = privilegeService.getCustomerPrivilege()
        val accountRole = AccountRole(userId = account.id, roleId = customerPrivilege.id)
        accountRoleRepo.save(accountRole)
    }

    fun getOne(id: Long) = accountRepo.getOne(id)
}