package com.ridi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
@EnableAsync
class SomethingForATeamApplication

fun main(args: Array<String>) {
    SpringApplication.run(SomethingForATeamApplication::class.java, *args)
}
