package com.ridi.common

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.Privilege
import java.util.*

fun dummyAccount(
    username: String = "test_username",
    name: String = "test_name",
    password: String = "test_password",
    phone: String = "test_phone",
    birth: Date? = null
) = Account(
    username = username,
    name = name,
    password = password,
    phone = phone,
    birth = birth
)

fun dummyPrivilege(
    name: String = "test_name",
    codename: String = "test_codename"
) = Privilege(
    name = name,
    codename = codename
)
