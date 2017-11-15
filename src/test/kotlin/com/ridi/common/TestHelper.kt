package com.ridi.common

import com.ridi.domain.videoshop.account.model.Account

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
