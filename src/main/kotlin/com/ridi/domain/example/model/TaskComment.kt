package com.ridi.domain.example.model

import javax.persistence.*
import java.util.Date

@Entity
@Table(name = "task_comment")
data class TaskComment(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column val content: String,
    @Column(name = "created_at") val createdAt: Date = Date(),
    @JoinColumn(name = "task_id") @ManyToOne(targetEntity = Task::class) val task: Task
)