package com.ridi.domain.history.entity.repository

import com.ridi.common.dummyAccount
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.repository.AccountRepository
import org.jooq.DSLContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class EntityHistoryRepositoryTest {
    @Autowired
    lateinit var accountRepo: AccountRepository

    @Autowired
    @Qualifier("historyDsl")
    lateinit var jooq: DSLContext

    @Test
    fun test_Entity를_생성하면_로그가_기록된다() {
        accountRepo.save(dummyAccount())
        val count = jooq.fetch("SELECT COUNT(*) FROM account_history").getValue(0, 0) as Long
        assertTrue(count == 1L)
    }

   @Test
    fun test_History_테이블에는_rowId가_기록된다() {
        val account = dummyAccount()
        accountRepo.save(account)
        val count = jooq.fetch("SELECT COUNT(*) FROM account_history WHERE row_id = ?", account.id).getValue(0, 0) as Long
        assertTrue(count == 1L)
    }

    @Before
    fun setUp() {
        accountRepo.deleteAll()
        val tables = jooq.fetch("SHOW TABLES").map { it["TABLE_NAME"] }
        if ("account_history" in tables) {
            jooq.execute("DELETE FROM account_history")
        }

    }

    @After
    fun tearDown() {
        accountRepo.deleteAll()
        jooq.execute("DELETE FROM account_history")
    }
}