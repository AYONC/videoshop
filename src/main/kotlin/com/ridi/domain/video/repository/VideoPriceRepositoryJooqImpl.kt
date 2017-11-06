package com.ridi.domain.video.repository

import com.ridi.infra.repository.JooqBaseRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager


@Repository
class VideoPriceRepositoryJooqImpl(
        private val jooq: DSLContext,
        em: EntityManager
) : VideoPriceRepositoryJooq, JooqBaseRepository(em) {}
