package com.ridi.domain.videoshop.account.model


import com.ridi.common.EntityListener
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(EntityListener::class)
@Table(name = "account_permission")
data class AccountRole(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull val name: String,
    @Column @NotNull val codename: String
)
