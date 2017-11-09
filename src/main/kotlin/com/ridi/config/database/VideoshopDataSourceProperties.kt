package com.ridi.config.database

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "datasource.videoshop")
class VideoshopDataSourceProperties: BaseDataSourceProperties()
