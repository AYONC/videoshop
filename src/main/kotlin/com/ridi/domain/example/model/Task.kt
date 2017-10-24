package com.ridi.domain.example.model

import javax.persistence.*
import java.util.Date

@Entity
@Table(name = "task")
data class Task(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
        @Column val title: String,
        @Column val content: String,
        @JoinColumn(name = "member_id") @ManyToOne(targetEntity = Member::class) var member: Member? = null,
        @Column(name = "created_at") val createdAt: Date = Date()
) {
    fun assign(member: Member) {
        this.member = member
    }

    fun cancelAssign() {
        this.member = null
    }
}