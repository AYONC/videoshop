package com.ridi.config

import com.ridi.domain.account.service.AccountService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class SecurityConfig(
    var accountService: AccountService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/account/login").permitAll()
            .antMatchers("/account").hasAuthority("USER")
            .antMatchers("/admin").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginProcessingUrl("/login")
            .and()
            .logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
    }

    @Configuration
    class AuthenticationConfiguration(
        var userDetailsService: UserDetailsService
    ) : GlobalAuthenticationConfigurerAdapter() {
        val passwordEncoder: PasswordEncoder = AccountPasswordEncoder()

        override fun init(auth: AuthenticationManagerBuilder) {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
        }
    }

    // todo password encoding
    // sha-2 이상의 강력한 해시를 사용하거나 스프링에서 기본적으로 제공하는 BCryptPasswordEncoder
    class AccountPasswordEncoder : PasswordEncoder {
        override fun encode(rawPassword: CharSequence?): String {
            return "EN-" + rawPassword
        }

        override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
            return encodedPassword.equals(encode(rawPassword))
        }
    }
}
