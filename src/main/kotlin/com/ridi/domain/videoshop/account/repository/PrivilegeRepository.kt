package com.ridi.domain.videoshop.account.repository

import com.ridi.domain.videoshop.account.model.Privilege
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PrivilegeRepository : JpaRepository<Privilege, Long> {
    fun getByName(name: String): Privilege
    override fun delete(privilege: Privilege)
}
