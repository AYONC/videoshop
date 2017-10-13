package com.ridi.domain.post

import org.springframework.data.jpa.repository.JpaRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

import com.ridi.generated.tables.Post.POST
import org.jooq.Record

@Repository
interface JooqPostRepository {
    fun findTest1(): Post
    fun findTest2(): Post
}

@Repository
class JooqPostRepositoryImpl(
    private val jooq: DSLContext
) : JooqPostRepository {
    override fun findTest1(): Post =
        jooq.select().from(POST).where(POST.CONTENT.eq("test1")).fetchOne(mapper())

    override fun findTest2(): Post =
        jooq.select().from(POST).where(POST.CONTENT.eq("test2")).fetchOne(mapper())

    private fun mapper() = {
        r: Record -> Post(
            id = r.into(POST).id.toLong(),
            content = r.into(POST).content,
            createdAt = r.into(POST).createdAt,
            user = r.into(POST).user
        )
    }
}

@Repository
interface PostRepository: JpaRepository<Post, Long>, JooqPostRepository {
    fun findByUser(user: String): List<Post>
}
