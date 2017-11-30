package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.AccountRole
import com.ridi.domain.videoshop.account.repository.AccountRepository
import com.ridi.domain.videoshop.account.repository.AccountRoleRepository
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import javax.transaction.Transactional

@Service
class StaffService(
    val privilegeService: PrivilegeService,
    val accountRepo: AccountRepository,
    val accountRoleRepo: AccountRoleRepository
) {
    var QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl="
    var APP_NAME = "VideoShopProject"

    @Transactional
    fun createAsStaff(account: Account) {
        accountRepo.save(account)

        val staffPrivilege = privilegeService.getStaffPrivilege()
        val accountRole = AccountRole(userId = account.id, roleId = staffPrivilege.id)
        accountRoleRepo.save(accountRole)
    }

    @Transactional
    fun updateAccount(account: Account) {
        accountRepo.save(account)
    }

    @Throws(UnsupportedEncodingException::class)
    fun generateQRUrl(user: Account): String {
        return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, user.username, user.secret, APP_NAME), "UTF-8")
    }
}
