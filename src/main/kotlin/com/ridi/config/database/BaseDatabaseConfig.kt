package com.ridi.config.database

import com.ridi.config.database.datasource.BaseDataSourceProperties
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean

abstract class BaseDatabaseConfig {
    abstract fun dataSourceProps(): BaseDataSourceProperties
    abstract fun jpaVendorAdapter(): JpaVendorAdapter
    abstract fun packagesToScan(): Array<String>
    abstract fun persistenceUnitName(): String

    fun getDataSource() =
        BasicDataSource().apply {
            driverClassName = dataSourceProps().driverClassName
            url = dataSourceProps().url
            username = dataSourceProps().username
            password = dataSourceProps().password
        }

    fun getEntityManagerFactoryBean(): LocalContainerEntityManagerFactoryBean {
        val self = this
        return LocalContainerEntityManagerFactoryBean().apply {
            setJtaDataSource(self.getDataSource())
            jpaVendorAdapter = jpaVendorAdapter()
            setPackagesToScan(*packagesToScan())
            persistenceUnitName = persistenceUnitName()
            jpaPropertyMap = hashMapOf<String, Any?>(
                "hibernate.transaction.jta.platform" to AtomikosJtaPlatform::class.java.name,
                "javax.persistence.transactionType" to "JTA",
                "hibernate.enable_lazy_load_no_trans" to true
            )
        }
    }
}