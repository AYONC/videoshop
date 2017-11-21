package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.constants.RoleType
import com.ridi.domain.videoshop.account.repository.PrivilegeRepository
import org.springframework.stereotype.Service

@Service
class PrivilegeService(
    val privilegeRepo: PrivilegeRepository
) {
    fun getStaffPrivilege() = privilegeRepo.getByName(RoleType.STAFF.toString())
    fun getCustomerPrivilege() = privilegeRepo.getByName(RoleType.CUSTOMER.toString())
}
