package com.ridi.domain.videoshop.account.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.ridi.common.EntityListener
import com.ridi.common.toLocalDate
import com.ridi.domain.videoshop.account.constants.RoleType
import com.ridi.domain.videoshop.account.exception.CustomerAssertionFailedException
import org.jboss.aerogear.security.otp.api.Base32
import java.io.Serializable
import java.time.LocalDate
import java.time.Period
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(EntityListener::class)
@Table(name = "account")
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @JsonProperty("id") val id: Long = 0,
    @Column @NotNull val username: String,
    @Column @NotNull val name: String,
    @Column @NotNull val password: String,
    @Column @NotNull val phone: String,
    @Column @NotNull val birth: Date? = null,
    @Column(name = "is_using_2fa") @NotNull var isUsing2FA: Boolean = false,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "account_role",
        joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id"))
    )
    val privileges: Collection<Privilege> = mutableListOf()
) : Serializable {
    var secret: String = Base32.random()

    init {
        this.secret = Base32.random()
    }

    @Throws(CustomerAssertionFailedException::class)
    fun assertIsCustomer() {
        if (!isCustomer()) {
            throw CustomerAssertionFailedException()
        }
    }

    private fun isCustomer() =
        privileges.filter { it.codename == RoleType.CUSTOMER.toString() }.count() > 0

    fun getAge(): Int? {
        if (birth != null) {
            return Period.between(birth.toLocalDate(), LocalDate.now()).years
        }
        return null
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + username.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (javaClass != obj.javaClass) {
            return false
        }
        val user = obj as Account?
        return (username == user!!.username)
    }

    override fun toString(): String =
        "Account [id=$id, username=$username, password=$password, isUsing2FA=$isUsing2FA, secret=$secret, privileges=$privileges]"
}
