package com.ridi.domain.history.entity.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.ridi.common.getAttr
import com.ridi.common.isAnyNull
import com.ridi.infra.repository.JooqBaseRepository
import org.jooq.DSLContext
import org.jooq.impl.DSL.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.Table

@Repository
class EntityHistoryRepository(
    @Qualifier("historyDsl") private val jooq: DSLContext,
    em: EntityManager
) : JooqBaseRepository(em) {
    private var existingTableNames: MutableList<String> = mutableListOf()

    private fun getTableName(entity: Any) : String {
        val entityTableName = entity::class.java.getAnnotation(Table::class.java).name
        return "${entityTableName}_history"
    }

    @Synchronized
    fun createTableIfNotExists(entity: Any) {
        if (existingTableNames.size == 0) {
            existingTableNames = jooq.meta().tables.map { it.name }.toMutableList()
        }
        val tableName = getTableName(entity)

        if (tableName !in existingTableNames) {
            jooq.query("""
                CREATE TABLE `$tableName` (
                    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                    `row_id` int(11) NOT NULL,
                    `json` text,
                    `reg_date` datetime NOT NULL,
                    PRIMARY KEY (`id`),
                    KEY `row_id` (`row_id`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
            """.trimIndent())
                .execute()
            existingTableNames.add(tableName)
        }
    }

    fun createHistory(entity: Any) {
        createTableIfNotExists(entity)

        val tableName = getTableName(entity)
        val rowId = getAttr(entity, "id")
        val json = ObjectMapper().writeValueAsString(entity)

        if (isAnyNull(rowId, json)) {
            return
        }

        jooq.insertInto(table(name(tableName)))
            .set(field(name("row_id")), rowId)
            .set(field(name("json")), json)
            .set(field(name("reg_date")), Date())
            .execute()
    }
}