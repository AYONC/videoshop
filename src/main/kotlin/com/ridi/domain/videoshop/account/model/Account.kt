package com.ridi.domain.videoshop.account.model

import com.ridi.common.EntityListener
import com.ridi.common.toLocalDate
import com.ridi.domain.videoshop.account.constants.RoleType
import com.ridi.domain.videoshop.account.exception.CustomerAssertionFailedException
import org.jboss.aerogear.security.otp.api.Base32
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
    @Column(name = "is_using_2fa") @NotNull val isUsing2FA: Boolean = false,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "account_role",
        joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id"))
    )
    val privileges: Collection<Privilege> = mutableListOf()
) {
    var secret: String = Base32.random()
    var enabled: Boolean = false

    init {
        this.secret = Base32.random()
        this.enabled = false
    }

    fun assertIsCustomer() {
        if (!isCustomer()) {
            throw CustomerAssertionFailedException()
        }
    }

    fun isCustomer() = privileges.filter { it.codename == RoleType.CUSTOMER.toString() }.count() > 0

    fun getAge(): Int? {
        if (birth != null) {
            return Period.between(birth.toLocalDate(), LocalDate.now()).years
        }
        return null
    }

    override fun toString() = username
}
