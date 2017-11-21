package com.ridi.domain.videoshop.account.model

import javax.persistence.*


@Entity
@Table(name = "privilege")
data class Privilege(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,

    @Column val name: String = "",
    @Column val codename: String = "",

    @ManyToMany(mappedBy = "privileges")
    private val accounts: Collection<Account> = mutableListOf()
) {
    override fun toString() = name
}
