package com.ridi

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.repository.AccountRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    lateinit var accountRepo: AccountRepository

    @Test
    fun getAccount() {
        val account1: Account = accountRepo.findByUsername("aychoi").get(0)
        val account2: Account = accountRepo.findById(42).get()
        assertNotNull(account1)
        assertNotNull(account2)
        assertEquals(account1, account2)
    }

}