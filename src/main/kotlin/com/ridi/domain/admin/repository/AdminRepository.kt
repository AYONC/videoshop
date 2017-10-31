package com.ridi.domain.admin.repository

import com.ridi.domain.admin.model.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository interface AdminRepository: JpaRepository<Admin, Long>
