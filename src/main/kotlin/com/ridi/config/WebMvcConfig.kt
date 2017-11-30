package com.ridi.config

import com.ridi.common.intercepter.TestInterceptor
import com.ridi.domain.videoshop.video.util.AgeRatingConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class WebMvcConfig : WebMvcConfigurerAdapter() {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(TestInterceptor())
    }

    @Override
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/")
    }

    @Override
    override fun addFormatters(registry: FormatterRegistry?) {
        registry!!.addConverter(AgeRatingConverter())
        super.addFormatters(registry)
    }
}
