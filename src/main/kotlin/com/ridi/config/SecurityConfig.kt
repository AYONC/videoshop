package com.ridi.config


import com.ridi.domain.videoshop.account.service.AccountService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig(
    var accountService: AccountService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/account/login").permitAll()
            .antMatchers("/account/logout").permitAll()
            .antMatchers("/**").authenticated()
            .anyRequest().authenticated()
            .and()

            .formLogin()
            .loginProcessingUrl("/account/login")
            .loginPage("/account/login")
            .and()

            .logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/account/login")
            .permitAll()
    }

    @Configuration
    class AuthenticationConfiguration(
        var userDetailsService: UserDetailsService
    ) : GlobalAuthenticationConfigurerAdapter() {

        override fun init(auth: AuthenticationManagerBuilder) {
            auth.userDetailsService(userDetailsService).passwordEncoder(AccountPasswordEncoder())
        }

    }

    // todo password encoding
    // sha-2 이상의 강력한 해시를 사용하거나 스프링에서 기본적으로 제공하는 BCryptPasswordEncoder
    class AccountPasswordEncoder : BCryptPasswordEncoder() {
        override fun encode(rawPassword: CharSequence?): String {
            println(super.encode(rawPassword))
            return super.encode(rawPassword)
        }

        override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
            println(super.encode(rawPassword))
            return true//super.matches(rawPassword, encodedPassword)
        }
    }
}
