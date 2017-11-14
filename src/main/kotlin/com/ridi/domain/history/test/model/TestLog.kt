package com.ridi.domain.history.test.model

import javax.persistence.*

@Entity
@Table(name = "test_log")
data class TestLog(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column val user: String,
    @Column val content: String
)
