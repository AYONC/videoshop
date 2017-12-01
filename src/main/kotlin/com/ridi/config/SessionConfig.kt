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

    internal fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.addMixIn(Account::class.java, AccountMix::class.java)
        mapper.addMixIn(
            CommonUsernamePasswordAuthenticationToken::class.java,
            CommonUsernamePasswordAuthenticationTokenMix::class.java
        )
        mapper.registerModules(SecurityJackson2Modules.getModules(this.loader))
        return mapper
    }

    override fun setBeanClassLoader(classLoader: ClassLoader) {
        this.loader = classLoader
    }
}

class CustomerRedisSerializer(objectMapper: ObjectMapper) : GenericJackson2JsonRedisSerializer(objectMapper)


