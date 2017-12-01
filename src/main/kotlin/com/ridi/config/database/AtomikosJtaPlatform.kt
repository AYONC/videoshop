package com.ridi.config.database

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform
import javax.transaction.TransactionManager
import javax.transaction.UserTransaction

class AtomikosJtaPlatform : AbstractJtaPlatform() {
    companion object {
        internal var tm: TransactionManager? = null
        internal var ut: UserTransaction? = null
    }

    override fun locateTransactionManager(): TransactionManager? = tm
    override fun locateUserTransaction(): UserTransaction? = ut
}
