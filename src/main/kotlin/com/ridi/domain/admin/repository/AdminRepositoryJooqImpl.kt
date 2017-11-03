package com.ridi.domain.admin.repository

import com.ridi.infra.repository.JooqBaseRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class AdminRepositoryJooqImpl(
    private val jooq: DSLContext,
    em: EntityManager
) : AdminRepositoryJooq, JooqBaseRepository(em) {
    override fun grant() {
        // todo implement
    }
}
