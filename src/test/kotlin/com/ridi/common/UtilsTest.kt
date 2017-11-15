package com.ridi.common

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
class UtilsTest {
    @Test
    fun test_isAnyNull() {
        assertTrue(isAnyNull("a", "b", null, "d"))
        assertFalse(isAnyNull("a", "b", "c", "d"))
    }

    @Test
    fun test_getAttr_함수는_private_field를_가져올_수_있다() {
        val originalMsg = "test_msg"
        class Test(private val msg: String)
        val test = Test(originalMsg)
        val msg = getAttr(test, "msg")
        assertEquals(msg, originalMsg)
    }
}