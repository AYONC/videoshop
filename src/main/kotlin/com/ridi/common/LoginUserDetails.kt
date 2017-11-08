package com.ridi.common

import com.ridi.domain.account.model.Account
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User

class LoginUserDetails constructor(val account: Account) : User(
    account.name,
    account.password,
    AuthorityUtils.createAuthorityList(account.getRole())
)
