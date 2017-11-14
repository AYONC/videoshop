package com.ridi.domain.videoshop.account.repository

import com.ridi.domain.videoshop.account.model.Privilege
import org.springframework.data.jpa.repository.JpaRepository


interface PrivilegeRepository : JpaRepository<Privilege, Long> {
    fun findByName(name: String): Privilege

    override fun delete(privilege: Privilege)
}
