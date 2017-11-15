package com.ridi.domain.videoshop.account.service

import com.ridi.common.dummyAccount
import com.ridi.domain.videoshop.account.repository.AccountRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class AccountServiceTest {
    @Autowired
    lateinit var accountRepo: AccountRepository

    @Autowired
    lateinit var accountService: AccountService

    @Test
    fun test_Account_생성_및_조회() {
        val account = dummyAccount()
        accountService.create(account)

        val loadedAccounts = accountService.getAccount(account.username)

        assertTrue(loadedAccounts.size == 1)
        assertEquals(account, loadedAccounts[0])
    }

    @Before
    @After
    fun clean() {
        accountRepo.deleteAll()
    }
}