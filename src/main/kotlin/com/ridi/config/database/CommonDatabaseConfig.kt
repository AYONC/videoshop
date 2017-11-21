package com.ridi.config.database

import com.atomikos.icatch.jta.UserTransactionImp
import com.atomikos.icatch.jta.UserTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.jta.JtaTransactionManager

@Configuration
@ComponentScan
@EnableTransactionManagement
class CommonDatabaseConfig {
    @Bean
    fun propertySourcesPlaceholderConfigurer() = PropertySourcesPlaceholderConfigurer()

    @Bean
    fun jpaVendorAdapter() =
        HibernateJpaVendorAdapter().apply {
            setShowSql(true)
            setGenerateDdl(true)
            setDatabase(Database.MYSQL)
        }

    @Bean(name = arrayOf("userTransaction"))
    fun userTransaction() =
        UserTransactionImp().apply {
            setTransactionTimeout(10000)
        }

    @Bean(name = arrayOf("atomikosTransactionManager"), initMethod = "init", destroyMethod = "close")
    fun atomikosTransactionManager(): UserTransactionManager {
        val userTransactionManager = UserTransactionManager().apply {
            forceShutdown = false
        }

        AtomikosJtaPlatform.tm = userTransactionManager
        return userTransactionManager
    }

    @Bean(name = arrayOf("transactionManager"))
    @DependsOn(*arrayOf("userTransaction", "atomikosTransactionManager"))
    fun transactionManager(): JtaTransactionManager {
        val userTransaction = userTransaction()
        AtomikosJtaPlatform.ut = userTransaction
        return JtaTransactionManager(userTransaction, atomikosTransactionManager())
    }
}