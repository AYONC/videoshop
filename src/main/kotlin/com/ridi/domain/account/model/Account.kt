package com.ridi.domain.account.model

import com.ridi.common.RoleType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
/*
class Account: UserDetails {
    private var username: String = ""
    private var password: String = ""

    private var isAccountNonExpired: Boolean = false
    private var isAccountNonLocked: Boolean = false
    private var isCredentialsNonExpired: Boolean = false
    private var isEnabled: Boolean = false

    private var authorities: MutableCollection<GrantedAuthority>? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return isEnabled
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return isCredentialsNonExpired
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return isAccountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return isAccountNonLocked
    }
}
*/
@Entity
@Table(name = "admin")
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column(name="username", unique=true) @NotNull val name: String,
    @Column @NotNull val password: String,
    @Column @NotNull val phone: String,
    @Column @NotNull val level: Number = 1,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
) {
    fun getRole() = RoleType.STAFF.toString()
}