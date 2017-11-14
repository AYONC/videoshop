package com.ridi.domain.example.member.dto

import com.ridi.common.dto.EntityConvertible
import com.ridi.domain.example.member.model.Member
import javax.validation.constraints.NotEmpty


data class AddMemberRequest (
    @NotEmpty
    private val name: String
) : EntityConvertible {
    override fun toEntity() = Member(name = name)
}