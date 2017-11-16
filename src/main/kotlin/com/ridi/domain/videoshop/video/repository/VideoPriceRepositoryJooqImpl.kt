package com.ridi.domain.videoshop.video.repository

import com.ridi.domain.videoshop.video.model.VideoPrice
import com.ridi.generated.tables.VideoPrice.VIDEO_PRICE
import com.ridi.infra.repository.JooqBaseRepository
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager


@Repository
class VideoPriceRepositoryJooqImpl(
        @Qualifier("videoshopDsl") private val jooq: DSLContext,
        em: EntityManager
) : VideoPriceRepositoryJooq, JooqBaseRepository(em) {
    override fun getActivePriceByVideoId(videoId: Long): VideoPrice? =
        fetchEntities(
                jooq.select()
                        .from(VIDEO_PRICE)
                        .where(VIDEO_PRICE.VIDEO_ID.equal(videoId), VIDEO_PRICE.IS_ACTIVE.equal(1)),
                VideoPrice::class.java
        ).getOrNull(0)
}
