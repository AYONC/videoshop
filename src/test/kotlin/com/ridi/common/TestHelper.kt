package com.ridi.common

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.Privilege

fun dummyAccount(
    username: String = "test_username",
    name: String = "test_name",
    password: String = "test_password",
    phone: String = "test_phone"
) = Account(
    username = username,
    name = name,
    password = password,
    phone = phone
)

fun dummyPrivilege(
    name: String = "test_name",
    codename: String = "test_codename"
) = Privilege(
    name = name,
    codename = codename
)
