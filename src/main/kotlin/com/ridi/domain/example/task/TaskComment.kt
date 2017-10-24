package com.ridi.domain.example.task

import javax.persistence.*
import java.util.Date
import javax.validation.constraints.NotNull

@Entity
@Table(name = "task_comment")
data class TaskComment(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
        @Column @NotNull val content: String,
        @Column(name = "created_at") @NotNull val createdAt: Date = Date(),
        @JoinColumn(name = "task_id") @ManyToOne(targetEntity = Task::class) @NotNull val task: Task
)