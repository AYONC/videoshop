package com.ridi.config.database.prod

import com.ridi.config.database.BaseDatabaseConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

@Configuration
@Profile("prod")
class DatabaseConfigProd : BaseDatabaseConfig() {
    @Autowired
    lateinit var dataSourceProps: VideoshopDataSourcePropertiesProd

    @Bean
    fun dataSource(): DataSource = createDataSource(dataSourceProps)
}