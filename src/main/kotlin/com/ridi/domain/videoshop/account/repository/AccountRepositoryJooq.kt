package com.ridi.domain.videoshop.account.repository

import com.ridi.domain.videoshop.account.model.Account
import org.springframework.stereotype.Repository

@Repository
interface AccountRepositoryJooq {
    fun findByUsername(username: String): List<Account>
}
