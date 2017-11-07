package com.ridi.domain.account.repository

import com.ridi.domain.account.model.Account
import org.springframework.stereotype.Repository

@Repository
interface AccountRepositoryJooq {
    fun findByUsername(username: String): List<Account>
}