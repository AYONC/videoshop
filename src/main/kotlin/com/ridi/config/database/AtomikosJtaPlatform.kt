package com.ridi.config.database

import javax.transaction.TransactionManager
import javax.transaction.UserTransaction

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform

class AtomikosJtaPlatform : AbstractJtaPlatform() {
    companion object {
        internal var tm: TransactionManager? = null
        internal var ut: UserTransaction? = null
    }

    override fun locateTransactionManager(): TransactionManager? {
        return tm
    }

    override fun locateUserTransaction(): UserTransaction? {
        return ut
    }
}
