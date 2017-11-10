package com.ridi.config.database

import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HistoryJooqConfig {
    @Autowired
    lateinit var dbConfig: HistoryDatabaseConfig

    @Bean(name = arrayOf("historyConnectionProvider"))
    fun historyConnectionProvider() = DataSourceConnectionProvider(dbConfig.historyDataSource())

    @Bean(name = arrayOf("historyJooqConfiguration"))
    fun historyJooqConfiguration() =
        DefaultConfiguration().apply {
            set(dbConfig.historyDataSource())
            set(historyConnectionProvider())
            set(SQLDialect.MYSQL)
        }

    @Bean(name = arrayOf("historyDsl"))
    fun historyDsl() = DefaultDSLContext(historyJooqConfiguration())
}