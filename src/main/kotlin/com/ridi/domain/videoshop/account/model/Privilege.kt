package com.ridi.domain.videoshop.account.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "privilege")
data class Privilege(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @JsonProperty("id") val id: Long = 0,

    @Column val name: String = "",
    @Column val codename: String = "",

    @ManyToMany(mappedBy = "privileges")
    @JsonIgnore
    private val accounts: Collection<Account> = mutableListOf()
) : Serializable {
    override fun toString() = "Privilege [id=$id, name=$name, codename=$codename]"
}
