package com.ridi.domain.videoshop.account.dto

import javax.validation.constraints.NotEmpty

data class AddGroupRequest(
    @NotEmpty
    val title: String
)