package com.ridi.domain.videoshop.account.dto

import javax.validation.constraints.NotEmpty


data class LoginRequest(
    @NotEmpty
    private val username: String,
    @NotEmpty
    private val password: String
)
