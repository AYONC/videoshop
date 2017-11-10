package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.model.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService


interface AccountService : UserDetailsService {
    fun getAuthorities(usename: String): Collection<GrantedAuthority>
    fun getAccount(username: String): List<Account>
    fun create(account: Account)
}
