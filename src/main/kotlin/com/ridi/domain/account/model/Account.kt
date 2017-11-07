package com.ridi.domain.account.model

import com.ridi.common.RoleType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "account")
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column(name="username", unique=true) @NotNull val username: String,
    @Column @NotNull val name: String,
    @Column @NotNull val password: String,
    @Column @NotNull val phone: String,
    @Column @NotNull val level: Number = 1,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
) {
    fun getRole() = RoleType.STAFF.toString()
}
