package com.ridi.domain.videoshop.account.model


import com.ridi.common.EntityListener
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@EntityListeners(EntityListener::class)
@Table(name = "account_role")
data class AccountRole(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column(name = "user_id") @NotNull val userId: Long,
    @Column(name = "role_id") @NotNull val roleId: Long
) {
    /*
        @JoinColumn(name = "user_id")
        @OneToOne(targetEntity = Account::class)
        var account: Account? = null
    */
    @ManyToMany
    @JoinTable(
        name = "account",
        joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "account_id", referencedColumnName = "id"))
    )
    var accounts: Collection<Account> = mutableSetOf()

    @ManyToMany
    @JoinTable(
        name = "privilege",
        joinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    )
    var privileges: Collection<Privilege> = mutableSetOf()
}
