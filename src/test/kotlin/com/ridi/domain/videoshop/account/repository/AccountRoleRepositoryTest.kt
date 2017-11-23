package com.ridi.domain.videoshop.account.repository

import com.ridi.common.dummyAccount
import com.ridi.common.dummyPrivilege
import com.ridi.common.initializePrivilege
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.AccountRole
import com.ridi.domain.videoshop.account.model.Privilege
import com.ridi.domain.videoshop.account.service.CustomerService
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
    @Autowired lateinit var accountRepo: AccountRepository
    @Autowired lateinit var customerService: CustomerService
    @Autowired lateinit var accountRoleRepo: AccountRoleRepository
    @Autowired lateinit var privilegeRepo: PrivilegeRepository

    @Test
    fun test_Account와_AccountRole_간_M2M_TEST() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        assertNotEquals(0, account.id)
        assertTrue(account.privileges.isNotEmpty())
    }

    @Before
    fun setUp() {
        initializePrivilege()
    }

    @After
    fun clear() {
        accountRepo.deleteAll()
        accountRoleRepo.deleteAll()
        privilegeRepo.deleteAll()
    }
}