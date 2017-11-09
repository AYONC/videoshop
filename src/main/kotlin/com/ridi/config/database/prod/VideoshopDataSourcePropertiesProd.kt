package com.ridi.config.database.prod

import com.ridi.config.database.BaseVideoshopDataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("prod")
@ConfigurationProperties(prefix = "datasource.videoshop")
class VideoshopDataSourcePropertiesProd : BaseVideoshopDataSourceProperties()