package com.ridi.domain.account.dto


import com.ridi.domain.account.model.Account
import javax.validation.constraints.NotEmpty


data class AddAccountRequest(
    @NotEmpty
    private val username: String,
    @NotEmpty
    private val password: String,
    @NotEmpty
    private val name: String,
    @NotEmpty
    private val phone: String
) {
    fun toEntity() = Account(username = username, password = password, name = name, phone = phone)
}
