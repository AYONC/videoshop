package com.ridi.domain.videoshop.account.model


import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class Customer(
    private var username: String,
    private var password: String
) : UserDetails {

    private var isAccountNonExpired: Boolean = true
    private var isAccountNonLocked: Boolean = true
    private var isCredentialsNonExpired: Boolean = true
    private var isEnabled: Boolean = true

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
