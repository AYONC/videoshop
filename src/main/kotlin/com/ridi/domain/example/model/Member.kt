package com.ridi.domain.example.model

import javax.persistence.*
import java.util.Date

@Entity
@Table(name = "member")
data class Member(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
        @Column val name: String,
        @Column(name = "created_at") val createdAt: Date = Date()
)