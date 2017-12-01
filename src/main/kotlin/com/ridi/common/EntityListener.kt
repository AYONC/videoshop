package com.ridi.common

import com.ridi.domain.history.entity.repository.EntityHistoryRepository
import javax.persistence.PostPersist

class EntityListener {
    @PostPersist
    fun methodInvokedAfterPersist(entity: Any) {
        SpringBeanHelper.getBean(EntityHistoryRepository::class.java).apply { createHistory(entity) }
    }
}
