package com.ridi.domain.videoshop.account.repository

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.AccountRole
import com.ridi.domain.videoshop.account.model.Privilege
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue


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
    fun test_Account와_AccountRole_간_M2M_TEST() {
        val account = Account(
            username = "test_username",
            name = "test_name",
            password = "test_password",
            phone = "test_phone"
        )
        accountRepo.save(account)
        assertNotEquals(0, account.id)

        val privilege = Privilege(
            name = "test_name",
            codename = "test_codename"
        )
        privilegeRepo.save(privilege)

        val role = AccountRole(
            userId = account.id,
            roleId = privilege.id
        )
        accountRoleRepo.save(role)

        assertTrue(account.roles.isNotEmpty())
        account.roles.forEach { println(it) }
    }

    @Before
    @After
    fun clear() {
        accountRepo.deleteAll()
        accountRoleRepo.deleteAll()
        privilegeRepo.deleteAll()
    }
}