package com.ridi.config.database.test

import com.ridi.config.database.BaseVideoshopDataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("test")
@ConfigurationProperties(prefix = "datasource.videoshop")
class VideoshopDataSourcePropertiesTest : BaseVideoshopDataSourceProperties()