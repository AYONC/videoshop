package com.ridi.domain.task.repository

import com.ridi.domain.task.model.Task
import com.ridi.generated.tables.Task.TASK
import com.ridi.infra.repository.JooqBaseRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class TaskRepositoryJooqImpl(
    private val jooq: DSLContext,
    em: EntityManager
): TaskRepositoryJooq, JooqBaseRepository(em) {

    override fun findAssigned(assignedMemberId: Long): List<Task> =
        fetchEntities(
            jooq.select()
                .from(TASK)
                .where(TASK.MEMBER_ID.equal(assignedMemberId)),
            Task::class.java
        )
}