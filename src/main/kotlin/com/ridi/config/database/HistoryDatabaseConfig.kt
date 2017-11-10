package com.ridi.config.database

import com.ridi.config.database.datasource.HistoryDataSourceProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaVendorAdapter

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
    basePackages = arrayOf("com.ridi.domain.history"),
    entityManagerFactoryRef = "historyEntityManagerFactoryBean",
    transactionManagerRef = "transactionManager"
)
class HistoryDatabaseConfig : BaseDatabaseConfig() {
    @Autowired
    lateinit var jpaVendorAdapter: JpaVendorAdapter
    @Autowired
    lateinit var dataSourceProps: HistoryDataSourceProperties

    override fun dataSourceProps() = dataSourceProps
    override fun jpaVendorAdapter() = jpaVendorAdapter
    override fun packagesToScan() = arrayOf(
        "com.ridi.domain.history"
    )
    override fun persistenceUnitName() = "historyPersistenceUnit"

    @Bean(name = arrayOf("historyDataSource"))
    fun historyDataSource() = getDataSource()

    @Bean(name = arrayOf("historyEntityManagerFactoryBean"))
    fun historyEntityManagerFactoryBean() = getEntityManagerFactoryBean()

    @Bean(name = arrayOf("historyEntityManagerFactory"))
    fun historyEntityManagerFactory() = historyEntityManagerFactoryBean().nativeEntityManagerFactory

    @Bean(name = arrayOf("historyEntityManager"))
    fun historyEntityManager() = historyEntityManagerFactory().createEntityManager()
}