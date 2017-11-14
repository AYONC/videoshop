package com.ridi.domain.videoshop.account.model


import com.ridi.common.EntityListener
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@EntityListeners(EntityListener::class)
@Table(name = "account_role")
data class AccountRole(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column(name = "user_id") @NotNull val userId: Long,
    @Column(name = "role_id") @NotNull val roleId: Long
)
