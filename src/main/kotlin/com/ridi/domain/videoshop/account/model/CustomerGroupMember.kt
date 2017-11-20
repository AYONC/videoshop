package com.ridi.domain.videoshop.account.model

import com.ridi.common.EntityListener
import com.ridi.domain.videoshop.account.constants.CustomerGroupRole
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(EntityListener::class)
@Table(name = "account")
data class CustomerGroupMember(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id") @NotNull val group: CustomerGroup,
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id") @NotNull val account: Account,
    @Column(name = "group_role") @NotNull val groupRole: CustomerGroupRole
)