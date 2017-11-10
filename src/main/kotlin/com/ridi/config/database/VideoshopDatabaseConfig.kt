package com.ridi.config.database

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaVendorAdapter

// TODO: jooq 에서 multi db 가능하게 되면 @Primary 삭제
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

    @Bean(name = arrayOf("videoshopDataSource"))
    fun videoshopDataSource() = createDataSource(dataSourceProps)

    @Bean(name = arrayOf("videoshopEntityManagerFactoryBean"))
    fun videoshopEntityManagerFactoryBean() =
        createEntityManagerFactoryBean(
            dataSource = videoshopDataSource(),
            jpaVendorAdapter = jpaVendorAdapter,
            packagesToScan = arrayOf(
                "com.ridi.domain.videoshop",
                "com.ridi.domain.example"
            ),
            persistenceUnitName = "videoshopPersistenceUnit"
        )

    @Bean(name = arrayOf("videoshopEntityManagerFactory"))
    fun videoshopEntityManagerFactory() = videoshopEntityManagerFactoryBean().nativeEntityManagerFactory

    @Bean(name = arrayOf("videoshopEntityManager"))
    @Primary
    fun videoshopEntityManager() = videoshopEntityManagerFactory().createEntityManager()!!
}