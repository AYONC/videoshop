package com.ridi.domain.history.test.repository

import com.ridi.domain.history.test.model.TestLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestLogRepository : JpaRepository<TestLog, Long>
