package com.ridi.infra.repository

import org.jooq.Query
import javax.persistence.EntityManager

open class JooqBaseRepository(
    private val em: EntityManager
) {
    fun <E> fetchEntities(query: Query, type: Class<E>): List<E> {
        println(query.sql)
        val result = em.createNativeQuery(query.sql, type)
        val values = query.bindValues
        for (i in values.indices) {
            result.setParameter(i + 1, values[i])
        }
        return result.resultList.filterNotNull().map { it as E }
    }
}
