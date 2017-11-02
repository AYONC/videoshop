package com.ridi.domain.account.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService


interface AccountService : UserDetailsService {
    fun getAuthorities(usename: String): Collection<GrantedAuthority>
}