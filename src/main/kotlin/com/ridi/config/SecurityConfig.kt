package com.ridi.config


import com.ridi.common.security.AccountAuthProvider
import com.ridi.common.security.AuthenticationFailureHandler
import com.ridi.common.security.AuthenticationSuccessHandler
import com.ridi.common.security.WebAuthenticationDetailsSource
import com.ridi.domain.videoshop.account.service.LoginService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig(
    val loginService: LoginService,
    val authenticationDetailsSource: WebAuthenticationDetailsSource,
    val authenticationSuccessHandler: AuthenticationSuccessHandler,
    val authenticationFailureHandler: AuthenticationFailureHandler
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authProvider())
    }

    override fun configure(http: HttpSecurity) {
        // Access
        http.authorizeRequests().apply {
            antMatchers("/account/login", "/account/registrationConform").permitAll()
            antMatchers("/account/logout").permitAll()
            antMatchers("/account/staff/register").permitAll()
            antMatchers("/account/customer/register").permitAll()
            antMatchers("/**").authenticated()
            anyRequest().authenticated() // 여기 설정된 이외의 모든 리퀘스트는 로그인 사용자에게만 허용
            and()
            // fixme: 권한별 접근 제어를 설정
            // permitAll() 로그인 하지 않은 사용자 포함 모두 허용
            // .antMatchers("/admin/**/").hasIpAddress()
            // .hasIpAddress() 특정 ip만 접근 가능하도록 설정
            // .hasRole .hasAnyRole 로 url 접근 권한 설정 가능
        }
        // Login
        http.formLogin().apply {
            loginProcessingUrl("/account/login")
            loginPage("/account/login")
            failureUrl("/account/login?error=true")
            successHandler(authenticationSuccessHandler)
            failureHandler(authenticationFailureHandler)
            authenticationDetailsSource(authenticationDetailsSource)
            and()
        }
        // Logout
        http.logout().apply {
            logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            logoutSuccessUrl("/account/login")
            permitAll()
        }
    }

    @Bean
    fun authProvider() =
        AccountAuthProvider().apply {
            setUserDetailsService(loginService)
            setPasswordEncoder(encoder())
        }

    @Bean
    fun encoder() = BCryptPasswordEncoder(11)

    @Bean
    fun sessionRegistry() = SessionRegistryImpl()
}
