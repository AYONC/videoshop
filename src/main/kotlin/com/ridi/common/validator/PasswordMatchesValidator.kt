package com.ridi.common.validator

import com.ridi.domain.videoshop.account.dto.AddAccountRequest
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class PasswordMatchesValidator : ConstraintValidator<PasswordMatches, Any> {
    override fun initialize(constraintAnnotation: PasswordMatches?) {}
    override fun isValid(obj: Any, context: ConstraintValidatorContext): Boolean {
        val user = obj as AddAccountRequest
        return user.password.equals(user.matchPassword)
    }
}