package com.ridi.domain.videoshop.account.model

import com.ridi.common.dummyAccount
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.util.*
import kotlin.test.assertEquals

//@RunWith(SpringRunner::class)
//@SpringBootTest
//@ActiveProfiles("test")
class AccountTest {
    @Test
    fun test_age() {
        val expectedAge = 10
        val birthYear = LocalDate.now().year - expectedAge
        val birth = Date(birthYear - 1900, 1, 1)
        val customer = dummyAccount(birth = birth)

        assertEquals(customer.getAge(), expectedAge)
    }
}
