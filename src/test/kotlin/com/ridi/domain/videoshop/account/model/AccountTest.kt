package com.ridi.domain.videoshop.account.model

import com.ridi.common.dummyAccount
import org.junit.Test
import java.time.LocalDate
import java.util.*
import kotlin.test.assertEquals

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
