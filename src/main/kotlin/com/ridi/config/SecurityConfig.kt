package com.ridi.config

import com.ridi.common.RoleType
import com.ridi.domain.account.service.AccountService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class SecurityConfig(
    var accountService: AccountService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and()
            .authorizeRequests()
            .antMatchers("/admin/**").hasAuthority(RoleType.ADMIN.toString())
            .antMatchers("/**").hasAuthority(RoleType.STAFF.toString())
            .anyRequest().authenticated()
//            .antMatchers("/index").permitAll()
            .and()
            .formLogin()
//            .loginPage("/account/login")
            .successHandler(RefererRedirectionAuthenticationSuccessHandler())
//            .loginProcessingUrl("/account/login")
            .permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
    }
/*

    @Bean
    fun httpSessionStrategy() = HeaderHttpSessionStrategy()

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
*/

    @Component
    class RefererRedirectionAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {
        var redirectStrategy = DefaultRedirectStrategy()

        init {
            setUseReferer(true)
        }

        override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
            redirectStrategy.sendRedirect(request, response, "/");
//            super.onAuthenticationSuccess(request, response, authentication)
        }
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
