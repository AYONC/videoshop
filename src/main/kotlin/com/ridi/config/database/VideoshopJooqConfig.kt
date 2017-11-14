package com.ridi.config.database

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class VideoshopJooqConfig : BaseJooqConfig() {
    @Autowired
    lateinit var dbConfig: VideoshopDatabaseConfig

    override fun dbConfig() = dbConfig

    @Bean(name = arrayOf("videoshopConnectionProvider"))
    fun videoshopConnectionProvider() = getConnectionProvider()

    @Bean(name = arrayOf("videoshopJooqConfiguration"))
    fun videoshopJooqConfiguration() = getJooqConfiguration()

    @Bean(name = arrayOf("videoshopDsl"))
    @Primary
    fun videoshopDsl() = getDsl()
}
