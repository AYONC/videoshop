package com.ridi.config.database

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

    @Bean(name = arrayOf("historyDataSource"))
    fun historyDataSource() = createDataSource(dataSourceProps)

    @Bean(name = arrayOf("historyEntityManagerFactoryBean"))
    fun historyEntityManagerFactoryBean() =
        createEntityManagerFactoryBean(
            dataSource = historyDataSource(),
            jpaVendorAdapter = jpaVendorAdapter,
            packagesToScan = arrayOf("com.ridi.domain.history"),
            persistenceUnitName = "historyPersistenceUnit"
        )

    @Bean(name = arrayOf("historyEntityManagerFactory"))
    fun historyEntityManagerFactory() = historyEntityManagerFactoryBean().nativeEntityManagerFactory

    @Bean(name = arrayOf("historyEntityManager"))
    fun historyEntityManager() = historyEntityManagerFactory().createEntityManager()!!
}