package com.ridi.config.database

import com.ridi.config.database.datasource.VideoshopDataSourceProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaVendorAdapter

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
    basePackages = arrayOf(
        "com.ridi.domain.videoshop",
        "com.ridi.domain.example"
    ),
    entityManagerFactoryRef = "videoshopEntityManagerFactoryBean",
    transactionManagerRef = "transactionManager"
)
class VideoshopDatabaseConfig : BaseDatabaseConfig() {
    @Autowired
    lateinit var jpaVendorAdapter: JpaVendorAdapter
    @Autowired
    lateinit var dataSourceProps: VideoshopDataSourceProperties

    override fun dataSourceProps() = dataSourceProps
    override fun jpaVendorAdapter() = jpaVendorAdapter
    override fun packagesToScan() = arrayOf(
        "com.ridi.domain.videoshop",
        "com.ridi.domain.example"
    )
    override fun persistenceUnitName() = "videoshopPersistenceUnit"

    @Bean(name = arrayOf("videoshopDataSource"))
    @Primary
    fun videoshopDataSource() = getDataSource()

    @Bean(name = arrayOf("videoshopEntityManagerFactoryBean"))
    fun videoshopEntityManagerFactoryBean() = getEntityManagerFactoryBean()

    @Bean(name = arrayOf("videoshopEntityManagerFactory"))
    @Primary
    fun videoshopEntityManagerFactory() = videoshopEntityManagerFactoryBean().nativeEntityManagerFactory

    @Bean(name = arrayOf("videoshopEntityManager"))
    @Primary
    fun videoshopEntityManager() = videoshopEntityManagerFactory().createEntityManager()
}
