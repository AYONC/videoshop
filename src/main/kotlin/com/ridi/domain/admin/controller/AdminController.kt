package com.ridi.domain.admin.controller



import com.ridi.domain.admin.dto.AddAdminRequest
import com.ridi.domain.admin.service.AdminService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

/*

 todo 1. admin 생성 권한추가
 todo 2. admin 계정 추가 Validation
 todo 3. admin 권한 레벨 별 할 수 있는 액션 정리

 */

@Controller
@RequestMapping("/admin/")
class AdminController(
    private val adminService: AdminService
) {
    @GetMapping("/add/")
    fun addAdminForm() = "admin/add_admin"

    @PostMapping("/add/")
    fun addAdmin(@Valid addAdminReq: AddAdminRequest): String {
        adminService.create(addAdminReq.toEntity())
        return "admin/add_admin_success"
    }

    @PostMapping("/update/")
    fun updateAdmin(@Valid addAdminReq: AddAdminRequest): String {
        adminService.create(addAdminReq.toEntity())
        return "admin/add_admin_success"
    }

    @GetMapping("/{adminId}/")
    fun admin(@PathVariable adminId: Long): Any {
        val admin = adminService.getOne(adminId)
        return admin
    }
}