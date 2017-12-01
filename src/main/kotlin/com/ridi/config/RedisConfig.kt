package com.ridi.config

import com.ridi.common.RedisDatabase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.session.data.redis.config.ConfigureRedisAction
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession


@Configuration
@EnableRedisHttpSession
class RedisConfig {

    @Value("\${spring.redis.host}") private val redisHost: String? = null
    @Value("\${spring.redis.port}") private val redisPort: Int = 0
    private val redisDatabase: Int = RedisDatabase.SESSION
//    @Value("\${spring.redis.password}") private val redisPassword: String? = null

    @Bean
    fun connectionFactory(): LettuceConnectionFactory =
        LettuceConnectionFactory(RedisStandaloneConfiguration().apply {
            hostName = redisHost!!
            port = redisPort
            database = redisDatabase
        })

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> =
        RedisTemplate<String, Any>().apply {
            connectionFactory = connectionFactory()
        }

    @Bean
    @Profile("dev", "test")
    fun configureRedisAction(): ConfigureRedisAction = ConfigureRedisAction.NO_OP
}
