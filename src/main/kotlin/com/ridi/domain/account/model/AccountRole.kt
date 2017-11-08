package com.ridi.domain.account.model


import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "account_permission")
data class AccountRole(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull val name: String,
    @Column @NotNull val codename: String
)
