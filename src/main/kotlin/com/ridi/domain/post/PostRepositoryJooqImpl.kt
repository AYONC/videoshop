package com.ridi.domain.post

import org.jooq.DSLContext
import org.springframework.stereotype.Repository

import com.ridi.generated.tables.Post.POST
import org.jooq.Record

@Repository
class PostRepositoryJooqImpl(
    private val jooq: DSLContext
) : PostRepositoryJooq {
    override fun findTest1(): Post =
        jooq.select()
            .from(POST)
            .where(POST.CONTENT.eq("test1"))
            .fetchOne(mapper())

    override fun findTest2(): Post =
        jooq.select()
            .from(POST)
            .where(POST.CONTENT.eq("test2"))
            .fetchOne(mapper())

    private fun mapper() = {
        r: Record -> Post(
            id = r.into(POST).id.toLong(),
            content = r.into(POST).content,
            createdAt = r.into(POST).createdAt,
            user = r.into(POST).user
        )
    }
}
