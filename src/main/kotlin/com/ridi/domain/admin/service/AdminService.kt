package com.ridi.domain.admin.service

import com.ridi.domain.admin.model.Admin
import com.ridi.domain.admin.repository.AdminRepository
import org.springframework.stereotype.Service

@Service
class AdminService (
    private val adminRepo: AdminRepository
) {
    fun create(admin: Admin) = adminRepo.save(admin)

    fun findAll() = adminRepo.findAll()

    fun getOne(id: Long) = adminRepo.getOne(id)
}
