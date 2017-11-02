package com.ridi.domain.account.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class AccountServiceImpl: AccountService {
    override fun loadUserByUsername(username: String): UserDetails? {
        return null
    }

    override fun getAuthorities(username: String): Collection<GrantedAuthority> {
        return mutableSetOf<GrantedAuthority>()
    }
}