package com.ridi.domain.videoshop.account.dto

import javax.validation.constraints.NotEmpty

data class InviteToGroupRequest(
    @NotEmpty
    val guestAccountId: Long
)