package com.ridi.domain.videoshop.account.repository

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.generated.tables.Account.ACCOUNT
import com.ridi.infra.repository.JooqBaseRepository
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class AccountRepositoryJooqImpl(
    @Qualifier("videoshopDsl") private val jooq: DSLContext,
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
