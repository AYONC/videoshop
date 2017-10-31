package com.ridi.domain.admin.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "admin")
data class Admin(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull val name: String,
    @Column @NotNull val phone: String,
    @Column @NotNull val level: Number,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
)