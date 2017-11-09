package com.ridi.domain.videoshop.account.model

import com.ridi.common.RoleType
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "account")
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull val username: String,
    @Column @NotNull val name: String,
    @Column @NotNull val password: String,
    @Column @NotNull val phone: String,
    @Column @NotNull val level: Int = 1,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
) {
    fun getRole() = RoleType.STAFF.toString()
}
