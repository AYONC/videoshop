package com.ridi.common

import com.ridi.domain.videoshop.account.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SpringBeanHelper
@Autowired
constructor(ac: ApplicationContext) {
    init {
        context = ac
    }

    companion object {
        var context: ApplicationContext? = null

        fun <B> getBean(clazz: Class<B>) = context!!.getBean(clazz)

        fun getCurrentUser() = SecurityContextHolder.getContext().authentication.principal as Account
    }
}
