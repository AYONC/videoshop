package com.ridi.common

import com.ridi.common.exception.NotFoundException
import org.slf4j.LoggerFactory

fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)

fun notFound(msg: String? = null) : Unit = throw NotFoundException(msg)
