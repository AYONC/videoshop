package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.AccountRole
import com.ridi.domain.videoshop.account.model.LoginUser
import com.ridi.domain.videoshop.account.model.Privilege
import com.ridi.domain.videoshop.account.repository.AccountRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class AccountServiceImpl(
    private var accountRepo: AccountRepository
) : AccountService {

    override fun getAccount(username: String): List<Account> {
        return accountRepo.findByUsername(username)
    }

    override fun create(account: Account) {
        accountRepo.save(account)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        try {
            val account = accountRepo.findByUsername(username).get(0)
            return LoginUser(
                id = account.username,
                pwd = account.password,
                enabled = true,
                accountNonExpired = true,
                credentialsNonExpired = true,
                accountNonLocked = true,
                createAuthorityList = getAuthorities(account.roles))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun getAuthorities(roles: Collection<AccountRole>): List<GrantedAuthority> {
        return getGrantedAuthorities(getPrivileges(roles))
    }

    private fun getPrivileges(roles: Collection<AccountRole>): List<String> {
        val privileges = ArrayList<String>()
        val collection = ArrayList<Privilege>()
        for (role in roles) {
            collection.addAll(role.privileges.asIterable())
        }
        for (item in collection) {
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
