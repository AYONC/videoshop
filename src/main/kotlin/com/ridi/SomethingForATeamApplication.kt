package com.ridi

import com.ulisesbocchio.jasyptspringboot.environment.StandardEncryptableEnvironment
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
@EnableAsync
class SomethingForATeamApplication

fun main(args: Array<String>) {
    System.setProperty("spring.devtools.restart.enabled", "false")
    System.setProperty("spring.devtools.livereload.enabled", "true")
    System.setProperty("spring.thymeleaf.cache", "false")
    SpringApplicationBuilder()
            .environment(StandardEncryptableEnvironment())
            .sources(SomethingForATeamApplication::class.java).run(*args)
}
