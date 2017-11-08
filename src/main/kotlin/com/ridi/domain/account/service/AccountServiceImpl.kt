package com.ridi.domain.account.service

import com.ridi.domain.account.model.Account
import com.ridi.domain.account.model.Customer
import com.ridi.domain.account.repository.AccountRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
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

    override fun loadUserByUsername(username: String): UserDetails? {
        try {
            var accounts = accountRepo.findByUsername(username)
            for (account in accounts) {
                System.out.println(account)
            }
        } finally {

        }

        var customer: Customer = Customer(username = username, password = "1234")
        return customer
    }

    override fun getAuthorities(username: String): Collection<GrantedAuthority> {
        return mutableSetOf<GrantedAuthority>()
    }
}
