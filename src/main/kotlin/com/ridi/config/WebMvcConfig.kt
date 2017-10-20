package com.ridi.config

import com.ridi.common.intercepter.TestInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class WebMvcConfig : WebMvcConfigurerAdapter() {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(TestInterceptor())
    }
}