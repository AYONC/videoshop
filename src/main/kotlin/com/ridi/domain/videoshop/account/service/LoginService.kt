package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.dto.LoginUser
import com.ridi.domain.videoshop.account.repository.AccountRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class LoginService(
    val accountRepo: AccountRepository
) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val account = accountRepo.findByUsername(username).get(0)
        val codeNames = account.privileges.map { it.codename }
        val authorities = codeNames.map { SimpleGrantedAuthority(it) }

        return LoginUser.fromAccount(account = account, createAuthorityList = authorities)
    }
}
