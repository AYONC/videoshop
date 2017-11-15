package com.ridi.domain.videoshop.account.model

import javax.persistence.*


@Entity
@Table(name = "privilege")
data class Privilege(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,

    @Column var name: String = "",
    @Column var codename: String = "",

    @ManyToMany(mappedBy = "privileges")
    private var accounts: Collection<Account> = mutableListOf()
)
