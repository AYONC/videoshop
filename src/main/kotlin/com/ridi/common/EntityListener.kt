package com.ridi.common

import com.ridi.domain.history.entity.repository.EntityHistoryRepository
import javax.persistence.PostPersist

class EntityListener {
    @PostPersist
    fun methodInvokedAfterPersist(entity: Any) {
        val entityHistoryRepo = SpringBeanHelper.getBean(EntityHistoryRepository::class.java)
        entityHistoryRepo.createHistory(entity)
    }
}
