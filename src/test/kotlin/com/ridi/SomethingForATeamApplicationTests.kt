package com.ridi

import org.jooq.types.UInteger
import org.jooq.types.ULong
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

//@RunWith(SpringRunner::class)
//@SpringBootTest
//@ActiveProfiles("test")
class SomethingForATeamApplicationTests {
    @Test
    fun test() {
        val value = 11L
        println(ULong.valueOf(value))
        println(UInteger.valueOf(value))
    }
}
