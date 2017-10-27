package com.ridi.domain.member

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "member")
data class Member(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull val name: String,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
)