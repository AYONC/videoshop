package com.ridi.domain.videoshop.account.provider


//import org.jboss.aerogear.security.otp.Totp

//@Component
/*
class AccountAuthProvider : DaoAuthenticationProvider() {

    @Autowired
    private val userRepository: AccountRepository? = null

    @Throws(AuthenticationException::class)
    override fun authenticate(auth: Authentication): Authentication {
        val verificationCode = (auth.details as AccountAuthProvider).getVerificationCode()
        val user = userRepository!!.findByEmail(auth.name) ?: throw BadCredentialsException("Invalid username or password")
        // MFA 관련 추가

//        if (user.isUsing2FA()) {
//            val totp = Totp(user.getSecret())
//            if (!isValidLong(verificationCode) || !totp.verify(verificationCode)) {
//                throw BadCredentialsException("Invalid verfication code")
//            }
//
//        }
        val result = super.authenticate(auth)
        return UsernamePasswordAuthenticationToken(user, result.credentials, result.authorities)
    }

    private fun isValidLong(code: String): Boolean {
        try {
            java.lang.Long.parseLong(code)
        } catch (e: NumberFormatException) {
            return false
        }

        return true
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}
*/