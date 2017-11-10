package com.ridi.domain.videoshop.account.repository

import com.ridi.domain.videoshop.account.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long>, AccountRepositoryJooq
