package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.LoginUser
import com.ridi.domain.videoshop.account.model.Privilege
import com.ridi.domain.videoshop.account.repository.AccountRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class AccountService(
    private val accountRepo: AccountRepository
) : UserDetailsService  {
    fun getAccount(username: String): List<Account> {
        return accountRepo.findByUsername(username)
    }

    fun create(account: Account) {
        accountRepo.save(account)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        try {
            val account = accountRepo.findByUsername(username).get(0)
            val roles = getAuthorities(account.privileges)
            for (role in roles) {
                println(role)
            }

            return LoginUser(
                id = account.username,
                pwd = account.password,
                enabled = true,
                accountNonExpired = true,
                credentialsNonExpired = true,
                accountNonLocked = true,
                createAuthorityList = roles)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun getAuthorities(roles: Collection<Privilege>): List<GrantedAuthority> {
        return getGrantedAuthorities(getPrivileges(roles))
    }

    private fun getPrivileges(roles: Collection<Privilege>): List<String> {
        val privileges = ArrayList<String>()
        val collection = ArrayList<Privilege>()
        for (item in roles) {
            privileges.add(item.codename)
        }

        return privileges
    }

    private fun getGrantedAuthorities(privileges: List<String>): List<GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        for (privilege in privileges) {
            authorities.add(SimpleGrantedAuthority(privilege))
        }
        return authorities
    }
}
