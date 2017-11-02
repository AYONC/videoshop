package com.ridi.config

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
//    @Value("\${spring.redis.database}") private val redisDatabase: Int = 0
//    @Value("\${spring.redis.password}") private val redisPassword: String? = null

    @Bean
    fun connectionFactory(): LettuceConnectionFactory {
        val config = RedisStandaloneConfiguration()
        config.hostName = redisHost!!
        config.port = redisPort
        return LettuceConnectionFactory(config)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = connectionFactory()
        return redisTemplate
    }

    @Bean
    @Profile("dev")
    fun configureRedisAction(): ConfigureRedisAction = ConfigureRedisAction.NO_OP
}
