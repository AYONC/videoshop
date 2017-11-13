package com.ridi.domain.videoshop.account.model


import com.ridi.common.EntityListener
import javax.persistence.*


@Entity
@EntityListeners(EntityListener::class)
@Table(name = "account_role")
data class AccountRole(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,

    @ManyToMany(mappedBy = "roles")
    var users: Collection<Account>? = null,

    @ManyToMany
    @JoinTable(
        name = "privilege",
        joinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    )
    var privileges: Collection<Privilege> = mutableSetOf()
)
