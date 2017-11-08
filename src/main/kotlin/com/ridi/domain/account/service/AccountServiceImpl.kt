package com.ridi.domain.account.service

import com.ridi.common.LoginUserDetails
import com.ridi.domain.account.model.Account
import com.ridi.domain.account.repository.AccountRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class AccountServiceImpl(
    private var accountRepo: AccountRepository
) : AccountService {
    override fun getAccount(username: String): List<Account> {
        return accountRepo.findByUsername(username)
    }

    override fun create(account: Account) {
        accountRepo.save(account)
    }

    override fun loadUserByUsername(username: String): UserDetails? {
        var account = accountRepo.findByUsername(username).get(0)
        return LoginUser(id=account.username, pwd = account.password, enabled=true,
                accountNonExpired=true, credentialsNonExpired=true,
                accountNonLocked=true, createAuthorityList = AuthorityUtils.createAuthorityList(account.getRole()))
        }

    override fun getAuthorities(username: String): Collection<GrantedAuthority> {
        return mutableSetOf<GrantedAuthority>()
    }
}

data class LoginUser(
        var id: String,
        var pwd: String,
        var enabled: Boolean,
        var accountNonExpired: Boolean,
        var credentialsNonExpired: Boolean,
        var accountNonLocked: Boolean,
        var createAuthorityList: List<GrantedAuthority>
): User(id, pwd, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, createAuthorityList)

