package com.ridi.domain.videoshop.account.dto


import com.ridi.common.dto.EntityConvertible
import com.ridi.domain.videoshop.account.model.Account
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
) : EntityConvertible {
    override fun toEntity() = Account(username = username, password = password, name = name, phone = phone)
}
