package com.ridi.domain.videoshop.account.dto

import com.ridi.domain.videoshop.account.model.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User


data class LoginUser(
    var id: String,
    var pwd: String,
    var enabled: Boolean,
    var accountNonExpired: Boolean,
    var credentialsNonExpired: Boolean,
    var accountNonLocked: Boolean,
    var createAuthorityList: List<GrantedAuthority>
) : User(id, pwd, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, createAuthorityList) {
    companion object {
        fun fromAccount(
            account: Account,
            enabled: Boolean = true,
            accountNonExpired: Boolean = true,
            credentialsNonExpired: Boolean = true,
            accountNonLocked: Boolean = true,
            createAuthorityList: List<GrantedAuthority> = listOf()
        ) = LoginUser(
            id = account.username,
            pwd = account.password,
            enabled = enabled,
            accountNonExpired = accountNonExpired,
            credentialsNonExpired = credentialsNonExpired,
            accountNonLocked = accountNonLocked,
            createAuthorityList = createAuthorityList
        )
    }
}
