package com.ridi

import com.ridi.domain.videoshop.account.model.AccountRole
import com.ridi.domain.videoshop.account.repository.AccountRepository
import com.ridi.domain.videoshop.account.repository.AccountRoleRepository
import com.ridi.domain.videoshop.account.repository.PrivilegeRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class AccountRoleRepositoryTest {
    @Autowired
    lateinit var accountRepo: AccountRepository

    @Autowired
    lateinit var accountRoleRepo: AccountRoleRepository

    @Autowired
    lateinit var privilegeRepo: PrivilegeRepository

    @Test
    fun getAccountRole() {
        var roles = accountRoleRepo.findByUserId(userId = 42)
        for (role in roles) {
            println(role)
        }
    }

    @Test
    fun addAccountRole() {
        var userId: Long = 42
        var roleId: Long = 5
        var accountRole = AccountRole(userId = userId, roleId = roleId)
        accountRoleRepo.save(accountRole)
    }

    @Test
    fun deleteAccountRole() {
        var roles = accountRoleRepo.findByUserId(userId = 42)
        for (role in roles) {
//            println(role.account)
//            println(role.role)
        }
    }
}