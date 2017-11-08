package com.ridi.config

import com.ridi.common.RoleType
import com.ridi.domain.account.service.AccountService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.session.web.http.HeaderHttpSessionStrategy
import org.springframework.session.web.http.HttpSessionStrategy
import org.springframework.session.MapSessionRepository
import org.springframework.session.SessionRepository
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig(
    var accountService: AccountService
) : WebSecurityConfigurerAdapter() {
//    override fun configure(builder: AuthenticationManagerBuilder?) {
//        builder!!.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//            .and()
            .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/**").hasAuthority("USER")
                .anyRequest().authenticated()
//            .antMatchers("/index").permitAll()
            .and()
            .formLogin()
//            .loginPage("/account/login")
//                .defaultSuccessUrl("/")
            .loginProcessingUrl("/login")
//                .successHandler(SuccessHandler())
            .and()
            .logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
    }
/*

    class SuccessHandler: AuthenticationSuccessHandler {
        override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
            DefaultRedirectStrategy().sendRedirect(request, response, "/index")
        }
    }*/
//    @Bean
//    fun successHandler() = RefererRedirectionAuthenticationSuccessHandler()
/*
    @Component
    class RefererRedirectionAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {
        var redirectStrategy = DefaultRedirectStrategy()

        init {
            setUseReferer(true)
        }

        override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
//            redirectStrategy.sendRedirect(request, response, "/");
            super.onAuthenticationSuccess(request, response, authentication)
        }
    }
*/
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

/*

@Configuration
@EnableSpringHttpSession
class HttpSessionConfig {
    @Bean
    fun httpSessionStrategy(): HttpSessionStrategy {
        return HeaderHttpSessionStrategy()
    }
}
*/
/*
class CommonLoginSuccessHandler(defaultUrl: String): SavedRequestAwareAuthenticationSuccessHandler() {
    init {
        defaultTargetUrl = defaultUrl
    }
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication?) {
        var session = request.getSession(false)
        var redirectUrl = session.getAttribute("prevPage").toString()
        if (redirectUrl != null) {
            session.removeAttribute("prevPage")
            redirectStrategy.sendRedirect(request, response, redirectUrl)
        } else {
//            super.onAuthenticationSuccess(request, response, authentication)
            redirectStrategy.sendRedirect(request, response, defaultTargetUrl)
        }
    }
}
*/
/*

@RestController
class UserController {
    @RequestMapping("/api/users")
    fun authorized()  = "Hello Secured World"
}
*/
