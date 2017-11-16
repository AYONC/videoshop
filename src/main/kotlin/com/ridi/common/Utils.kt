package com.ridi.common

import org.slf4j.LoggerFactory
import java.time.ZoneId
import java.util.*

fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)

fun isAnyNull(vararg args: Any?) = args.any { it == null }

fun getAttr(obj: Any, field: String) =
    obj::class.java.getDeclaredField(field)?.let {
        Date().toLocalDate()
        it.isAccessible = true
        it.get(obj)
    }

fun Date.toLocalDate() =
    this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
