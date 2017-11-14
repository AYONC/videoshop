package com.ridi

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.repository.AccountRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertTrue


@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    lateinit var accountRepo: AccountRepository

    @Test
    fun test_saveTest() {
        assertTrue(accountRepo.findAll().isEmpty())

        val account = Account(
            username = "test_username",
            name = "test_name",
            password = "test_password",
            phone = "test_phone"
        )
        accountRepo.save(account)

        assertTrue(accountRepo.findAll().size == 1)
    }

    @Before
    @After
    fun clear() {
        accountRepo.deleteAll()
    }
}