package com.ridi.domain.admin.repository

import org.springframework.stereotype.Repository

@Repository interface AdminRepositoryJooq {
    fun grant()
}