package com.ridi.domain.videoshop.account.repository

import com.ridi.domain.videoshop.account.model.CustomerGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerGroupRepository : JpaRepository<CustomerGroup, Long>