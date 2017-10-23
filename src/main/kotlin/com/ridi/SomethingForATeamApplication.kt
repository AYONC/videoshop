package com.ridi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.apache.commons.dbcp.BasicDataSource
import org.jooq.*
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.jooq.impl.DefaultRecordMapper
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@SpringBootApplication
@ComponentScan
@EnableScheduling
@EnableAutoConfiguration
@EnableAsync
class SomethingForATeamApplication {
    @Bean
    fun dataSource(): DataSource =
        BasicDataSource().apply {
            setDriverClassName("com.mysql.jdbc.Driver")
            setUrl("jdbc:mysql://storydev.cemib2jfp9g1.ap-northeast-2.rds.amazonaws.com:3306/tmp")
            setUsername("storydev")
            setPassword("rbx120303")
        }

    @Bean
	fun entityManagerFactory(): EntityManagerFactory =
        LocalContainerEntityManagerFactoryBean().apply {
            jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
                setGenerateDdl(true)
            }
            dataSource = dataSource()
            setPackagesToScan("com.ridi.domain")
            afterPropertiesSet()
        }.`object`

    @Bean
	fun transactionAwareDataSource(): TransactionAwareDataSourceProxy = TransactionAwareDataSourceProxy(this.dataSource())

	@Bean
	fun connectionProvider(): DataSourceConnectionProvider = DataSourceConnectionProvider(this.transactionAwareDataSource())

    @Bean
    fun configuration(): DefaultConfiguration {
        return DefaultConfiguration().apply {
            set(dataSource())
            set(connectionProvider())
            set(SQLDialect.MYSQL)
            set(object : RecordMapperProvider {
                override fun <R : Record?, E : Any?> provide(recordType: RecordType<R>?, type: Class<out E>?): RecordMapper<R, E> =
                    DefaultRecordMapper(recordType, type)
            })
        }
    }

    @Bean
    fun dsl(): DefaultDSLContext = DefaultDSLContext(this.configuration())

    @Bean
    fun transactionManager() = DataSourceTransactionManager(this.dataSource())
}

fun main(args: Array<String>) {
    SpringApplication.run(SomethingForATeamApplication::class.java, *args)
}
