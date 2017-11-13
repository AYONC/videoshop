package com.ridi.domain.videoshop.account.model

import com.ridi.common.EntityListener
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(EntityListener::class)
@Table(name = "account")
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull val username: String,
    @Column @NotNull val name: String,
    @Column @NotNull val password: String,
    @Column @NotNull val phone: String,
    @Column @NotNull val level: Int = 1,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "account_role",
        joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id"))
    )
    val roles: Collection<AccountRole> = mutableSetOf()
)
