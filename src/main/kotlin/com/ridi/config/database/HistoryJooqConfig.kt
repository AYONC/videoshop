package com.ridi.config.database

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HistoryJooqConfig : BaseJooqConfig() {
    @Autowired
    lateinit var dbConfig: HistoryDatabaseConfig

    override fun dbConfig() = dbConfig

    @Bean(name = arrayOf("historyConnectionProvider"))
    fun historyConnectionProvider() = getConnectionProvider()

    @Bean(name = arrayOf("historyJooqConfiguration"))
    fun historyJooqConfiguration() = getJooqConfiguration()

    @Bean(name = arrayOf("historyDsl"))
    fun historyDsl() = getDsl()
}