package com.ridi.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.ridi.common.security.CommonUsernamePasswordAuthenticationToken
import com.ridi.domain.videoshop.account.dto.AccountMix
import com.ridi.domain.videoshop.account.dto.CommonUsernamePasswordAuthenticationTokenMix
import com.ridi.domain.videoshop.account.model.Account
import org.springframework.beans.factory.BeanClassLoaderAware
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.security.jackson2.SecurityJackson2Modules
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession


@EnableRedisHttpSession
class SessionConfig : BeanClassLoaderAware {

    private var loader: ClassLoader? = null

    @Bean
    fun springSessionDefaultRedisSerializer(): RedisSerializer<Any> = CustomerRedisSerializer(objectMapper())

    @Bean
    fun connectionFactory(): LettuceConnectionFactory = LettuceConnectionFactory()

    internal fun objectMapper(): ObjectMapper = ObjectMapper().apply {
        addMixIn(Account::class.java, AccountMix::class.java)
        addMixIn(
            CommonUsernamePasswordAuthenticationToken::class.java,
            CommonUsernamePasswordAuthenticationTokenMix::class.java
        )
        registerModules(SecurityJackson2Modules.getModules(loader))
    }

    override fun setBeanClassLoader(classLoader: ClassLoader) {
        loader = classLoader
    }
}

class CustomerRedisSerializer(objectMapper: ObjectMapper) : GenericJackson2JsonRedisSerializer(objectMapper)


