package com.ridi.domain.videoshop.video.repository

import com.ridi.infra.repository.JooqBaseRepository
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager


@Repository
class VideoRepositoryJooqImpl(
    @Qualifier("videoshopDsl") private val jooq: DSLContext,
    em: EntityManager
) : VideoRepositoryJooq, JooqBaseRepository(em) {}