package com.ridi.config.database.dev

import com.ridi.config.database.BaseDatabaseConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

@Configuration
@Profile("dev")
class DatabaseConfigDev : BaseDatabaseConfig() {
    @Autowired
    lateinit var dataSourceProps: VideoshopDataSourcePropertiesDev

    @Bean
    fun dataSource(): DataSource = createDataSource(dataSourceProps)
}
