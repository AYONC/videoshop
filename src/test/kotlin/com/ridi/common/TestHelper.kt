package com.ridi.common

import com.ridi.domain.videoshop.account.constants.RoleType
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.Privilege
import com.ridi.domain.videoshop.account.repository.PrivilegeRepository
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

fun initializePrivilege() {
    val privilegeRepo = SpringBeanHelper.getBean(PrivilegeRepository::class.java)

    val codeNames = privilegeRepo.findAll().map { it.codename }
    val newPrivileges = RoleType.values().filter {
        it.toString() !in codeNames
    }

    newPrivileges.forEach {
        privilegeRepo.save(Privilege(name = it.toString(), codename = it.toString()))
    }
}
