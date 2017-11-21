package com.ridi.domain.videoshop.account.repository

import com.ridi.domain.videoshop.account.constants.CustomerGroupRole
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.CustomerGroup
import com.ridi.domain.videoshop.account.model.CustomerGroupMember
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerGroupMemberRepository : JpaRepository<CustomerGroupMember, Long> {
    fun getByAccountAndGroupRole(account: Account, groupRole: CustomerGroupRole): CustomerGroupMember
    fun countByAccount(account: Account): Long
    fun countByGroup(group: CustomerGroup): Long
}
