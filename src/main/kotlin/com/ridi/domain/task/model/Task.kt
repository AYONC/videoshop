package com.ridi.domain.task.model

import com.ridi.domain.member.model.Member
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "task")
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull val title: String,
    @Column @NotNull val content: String,
    @Column(name = "is_completed") @NotNull val isCompleted: Boolean = false,
    @JoinColumn(name = "member_id") @ManyToOne(targetEntity = Member::class) var member: Member? = null,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
) {
    fun assign(member: Member) {
        this.member = member
    }

    fun cancelAssign() {
        this.member = null
    }
}