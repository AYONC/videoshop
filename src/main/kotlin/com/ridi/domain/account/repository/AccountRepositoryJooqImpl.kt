package com.ridi.domain.account.repository

import com.ridi.domain.account.model.Account
import com.ridi.generated.tables.Account.ACCOUNT
import com.ridi.infra.repository.JooqBaseRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class AccountRepositoryJooqImpl(
    private val jooq: DSLContext,
    em: EntityManager
) : AccountRepositoryJooq, JooqBaseRepository(em) {
    override fun findByUsername(username: String): List<Account> =
        fetchEntities(
            jooq.select()
                .from(ACCOUNT)
                .where(ACCOUNT.USERNAME.equal(username)),
            Account::class.java
        )
}
