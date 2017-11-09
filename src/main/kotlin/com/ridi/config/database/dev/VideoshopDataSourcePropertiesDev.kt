package com.ridi.config.database.dev

import com.ridi.config.database.BaseVideoshopDataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev")
@ConfigurationProperties(prefix = "datasource.videoshop")
class VideoshopDataSourcePropertiesDev: BaseVideoshopDataSourceProperties()
