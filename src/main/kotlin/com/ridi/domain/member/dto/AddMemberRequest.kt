package com.ridi.domain.member

import javax.validation.constraints.NotEmpty

data class AddMemberRequest (
    @NotEmpty
    private val name: String
) {
    fun toEntity() = Member(name = name)
}