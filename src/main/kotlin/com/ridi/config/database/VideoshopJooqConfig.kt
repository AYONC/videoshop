package com.ridi.config.database

import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class VideoshopJooqConfig {
    @Autowired
    lateinit var dbConfig: VideoshopDatabaseConfig

    @Bean(name = arrayOf("videoshopConnectionProvider"))
    fun videoshopConnectionProvider() = DataSourceConnectionProvider(dbConfig.videoshopDataSource())

    @Bean(name = arrayOf("videoshopJooqConfiguration"))
    fun videoshopJooqConfiguration() =
        DefaultConfiguration().apply {
            set(dbConfig.videoshopDataSource())
            set(videoshopConnectionProvider())
            set(SQLDialect.MYSQL)
        }

    @Bean(name = arrayOf("videoshopDsl"))
    @Primary
    fun videoshopDsl() = DefaultDSLContext(videoshopJooqConfiguration())
}