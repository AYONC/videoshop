package com.ridi.domain.videoshop.account.repository


import com.ridi.domain.videoshop.account.model.AccountRole
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRoleRepository : JpaRepository<AccountRole, Long> {

    override fun delete(role: AccountRole)
}
