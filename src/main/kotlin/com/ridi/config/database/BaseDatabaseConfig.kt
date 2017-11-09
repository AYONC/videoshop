package com.ridi.config.database

import org.apache.commons.dbcp2.BasicDataSource

abstract class BaseDatabaseConfig {
    protected fun createDataSource(dataSourceProps: BaseVideoshopDataSourceProperties) =
        BasicDataSource().apply {
            driverClassName = dataSourceProps.driverClassName
            url = dataSourceProps.url
            username = dataSourceProps.username
            password = dataSourceProps.password
        }
}