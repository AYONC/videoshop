package com.ridi.domain.admin.dto


import com.ridi.domain.admin.model.Admin
import javax.validation.constraints.NotEmpty

data class AddAdminRequest(
    @NotEmpty
    private val name: String,
    @NotEmpty
    private val phone: String,
    @NotEmpty
    private val level: Number
) {
    fun toEntity() = Admin(name = name, phone = phone, level = level)
}