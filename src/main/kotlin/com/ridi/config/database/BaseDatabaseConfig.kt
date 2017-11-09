package com.ridi.config.database

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.persistence.EntityManager
import javax.sql.DataSource

abstract class BaseDatabaseConfig {
    protected fun createDataSource(dataSourceProps: BaseDataSourceProperties) =
        BasicDataSource().apply {
            driverClassName = dataSourceProps.driverClassName
            url = dataSourceProps.url
            username = dataSourceProps.username
            password = dataSourceProps.password
        }

    protected fun createEntityManagerFactoryBean(dataSource: DataSource, jpaVendorAdapter: JpaVendorAdapter, packagesToScan: Array<String>, persistenceUnitName: String): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            setJtaDataSource(dataSource)
            setJpaVendorAdapter(jpaVendorAdapter)
            setPackagesToScan(*packagesToScan)
            setPersistenceUnitName(persistenceUnitName)
            jpaPropertyMap = hashMapOf<String, Any?>(
                "hibernate.transaction.jta.platform" to AtomikosJtaPlatform::class.java.name,
                "javax.persistence.transactionType" to "JTA"
            )
        }
}