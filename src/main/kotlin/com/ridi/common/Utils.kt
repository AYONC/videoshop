package com.ridi.common

import org.slf4j.LoggerFactory

fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)

fun isAnyNull(vararg args: Any?) = args.any { it == null }

fun getAttr(obj: Any, field: String) =
    obj::class.java.getDeclaredField(field)?.let {
        it.isAccessible = true
        it.get(obj)
    }
