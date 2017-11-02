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
    SpringApplicationBuilder()
            .environment(StandardEncryptableEnvironment())
            .sources(SomethingForATeamApplication::class.java).run(*args)
}
