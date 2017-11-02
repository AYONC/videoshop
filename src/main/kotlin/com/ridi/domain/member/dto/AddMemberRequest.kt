package com.ridi.domain.member.dto

import com.ridi.domain.member.model.Member
import javax.validation.constraints.NotEmpty

data class AddMemberRequest (
    @NotEmpty
    private val name: String
) {
    fun toEntity() = Member(name = name)
}