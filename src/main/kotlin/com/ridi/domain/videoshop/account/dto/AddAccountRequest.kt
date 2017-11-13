package com.ridi.domain.videoshop.account.dto


import com.ridi.common.dto.EntityConvertible
import com.ridi.common.validator.PasswordMatches
import com.ridi.domain.videoshop.account.model.Account
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
) : EntityConvertible {

    override fun toEntity() = Account(username = username, password = password, name = name, phone = phone)

}
