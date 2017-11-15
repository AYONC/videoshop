package com.ridi.domain.videoshop.account.dto

import com.ridi.common.validator.PasswordMatches
import com.ridi.domain.videoshop.account.model.Account
import org.springframework.security.crypto.password.PasswordEncoder
import javax.validation.constraints.NotEmpty

@PasswordMatches
data class AddAccountRequest(
    @NotEmpty
    val username: String,
    @NotEmpty
    val password: String,
    @NotEmpty
    val matchPassword: String,
    @NotEmpty
    val name: String,
    @NotEmpty
    val phone: String
) {
    fun toEntity(passwordEncoder: PasswordEncoder) =
        Account(username = username, password = passwordEncoder.encode(password), name = name, phone = phone)
}
