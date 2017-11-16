package com.ridi.domain.videoshop.account.model

import com.ridi.common.EntityListener
import com.ridi.common.toLocalDate
import java.time.LocalDate
import java.time.Period
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
    @Column @NotNull val birth: Date? = null,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "account_role",
        joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id"))
    )
    val privileges: Collection<Privilege> = mutableListOf()
) {
    fun getAge(): Int? {
        if (birth != null) {
            return Period.between(birth.toLocalDate(), LocalDate.now()).years
        }
        return null
    }
}
