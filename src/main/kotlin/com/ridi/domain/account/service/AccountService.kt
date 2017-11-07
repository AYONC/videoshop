package com.ridi.domain.account.service

import com.ridi.domain.account.model.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService


interface AccountService: UserDetailsService {
    fun getAuthorities(usename: String): Collection<GrantedAuthority>
    fun getAccount(username: String): Account
    fun create(account: Account)
}
