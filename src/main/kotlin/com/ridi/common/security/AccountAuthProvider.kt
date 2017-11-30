package com.ridi.common.security


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.repository.AccountRepository
import org.jboss.aerogear.security.otp.Totp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CommonUsernamePasswordAuthenticationToken(
    @JsonProperty("principal") principal: Any,
    @JsonProperty("credentials") credentials: Any,
    @JsonProperty("authorities") authorities: Collection<GrantedAuthority>? = null
) : UsernamePasswordAuthenticationToken(principal, credentials, authorities)

class AccountAuthProvider(
) : DaoAuthenticationProvider() {

    @Autowired
    lateinit var userRepository: AccountRepository

    @Throws(AuthenticationException::class)
    override fun authenticate(auth: Authentication): Authentication {
        val verificationCode = (auth.details as AuthenticationDetails).verificationCode
        val user: Account = userRepository.findByUsername(auth.name)?.get(0)
            ?: throw BadCredentialsException("Invalid username or password")

        if (user.isUsing2FA) {
            val totp = Totp(user.secret)
            if (!isValidLong(verificationCode) || !totp.verify(verificationCode)) {
                throw BadCredentialsException("Invalid verfication code")
            }
        }
        val result = super.authenticate(auth)
        return CommonUsernamePasswordAuthenticationToken(user, result.credentials, result.authorities)
    }

    private fun isValidLong(code: String): Boolean {
        try {
            java.lang.Long.parseLong(code)
        } catch (e: NumberFormatException) {
            return false
        }

        return true
    }

    override fun supports(authentication: Class<*>) =
        authentication == UsernamePasswordAuthenticationToken::class.java

}
