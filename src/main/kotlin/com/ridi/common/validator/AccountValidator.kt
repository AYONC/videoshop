package com.ridi.common.validator


import com.ridi.domain.videoshop.account.dto.AddCustomerRequest
import com.ridi.domain.videoshop.account.dto.AddStaffRequest

import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

class AccountValidator : Validator {
    override fun supports(clazz: Class<*>) =
        AddCustomerRequest::class.java!!.isAssignableFrom(clazz) or
            AddStaffRequest::class.java!!.isAssignableFrom(clazz)

    override fun validate(obj: Any?, errors: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "message.name", "Name is required.")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "message.phone", "Phone is required.")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "message.password", "Password is required.")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "message.username", "UserName is required.")
    }
}
