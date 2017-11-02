package com.ridi.domain.account.model


import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class Customer: UserDetails {
    private var username: String = ""
    private var password: String = ""
//    private var name: String = ""
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
