package com.ridi.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

import com.ridi.domain.account.service.AccountService

@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {

    lateinit var accountService: AccountService

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/account/login").permitAll()
            .antMatchers("/account").hasAuthority("USER")
            .antMatchers("/admin").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .logout()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(accountService)
    }

}