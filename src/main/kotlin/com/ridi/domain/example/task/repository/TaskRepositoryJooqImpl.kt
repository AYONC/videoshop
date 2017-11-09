package com.ridi.domain.example.task.repository

import com.ridi.domain.example.task.model.Task
import com.ridi.generated.tables.Task.TASK
import com.ridi.infra.repository.JooqBaseRepository
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Qualifier
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
