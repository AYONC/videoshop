package com.ridi.domain.task.repository

import com.ridi.domain.member.Member
import com.ridi.domain.task.model.Task
import com.ridi.generated.tables.Member.MEMBER
import com.ridi.generated.tables.Task.TASK
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository

@Repository
class TaskRepositoryJooqImpl(
    private val jooq: DSLContext
): TaskRepositoryJooq {
    override fun findAssigned(assignedMemberId: Long): List<Task> =
        jooq.select()
            .from(TASK)
            .join(MEMBER).on(MEMBER.ID.equal(TASK.MEMBER_ID))
            .where(TASK.MEMBER_ID.equal(assignedMemberId))
            .fetch(mapper())

    private fun mapper() = {
        r: Record -> mapTask(r)
    }

    private fun mapTask(r: Record) = Task(
            id = r.into(TASK).id.toLong(),
            title = r.into(TASK).title,
            content = r.into(TASK).content,
            isCompleted = r.into(TASK).isCompleted,
            member = mapMember(r),
            createdAt = r.into(TASK).createdAt
    )

    private fun mapMember(r: Record) = Member(
        id = r.into(MEMBER).id.toLong(),
        name = r.into(MEMBER).name,
        createdAt = r.into(MEMBER).createdAt
    )
}