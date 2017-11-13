package com.ridi.domain.videoshop.account.model

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
) : User(id, pwd, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, createAuthorityList)
