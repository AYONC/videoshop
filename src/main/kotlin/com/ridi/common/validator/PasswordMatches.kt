package com.ridi.common.validator

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.ANNOTATION_CLASS)
@Retention(RUNTIME)
@Constraint(validatedBy = arrayOf(PasswordMatchesValidator::class))
@Documented
annotation class PasswordMatches @JvmOverloads constructor(
    val message: String = "Passwords don't match",
    val groups: Array<KClass<*>> = arrayOf(),
    val payload: Array<KClass<out Payload>> = arrayOf()
)
