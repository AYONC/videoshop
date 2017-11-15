package com.ridi.config.database

import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext

abstract class BaseJooqConfig {
    abstract fun dbConfig() : BaseDatabaseConfig

    fun getConnectionProvider() = DataSourceConnectionProvider(dbConfig().getDataSource())

    fun getJooqConfiguration() =
        DefaultConfiguration().apply {
            set(dbConfig().getDataSource())
            set(getConnectionProvider())
            set(SQLDialect.MYSQL)
            settings().isRenderSchema = false
        }

    fun getDsl() = DefaultDSLContext(getJooqConfiguration())
}