package com.ridi.domain.videoshop.account.model

import com.ridi.common.EntityListener
import com.ridi.common.SpringBeanHelper
import com.ridi.domain.videoshop.account.constants.CustomerGroupRole
import com.ridi.domain.videoshop.account.constants.GROUP_MAX_MEMBERS
import com.ridi.domain.videoshop.account.exception.CustomerGroupFullException
import com.ridi.domain.videoshop.account.repository.CustomerGroupMemberRepository
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(EntityListener::class)
@Table(name = "account")
data class CustomerGroup(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column @NotNull val title: String
) {
    private fun customerGroupMemberRepo() = SpringBeanHelper.getBean(CustomerGroupMemberRepository::class.java)

    fun getMemberCount() = customerGroupMemberRepo().countByGroup(this)

    private fun assertMemberCountNotExceed() {
        if (getMemberCount() > GROUP_MAX_MEMBERS) {
            throw CustomerGroupFullException()
        }
    }

    fun invite(guest: Account): CustomerGroupMember {
        assertMemberCountNotExceed()

        val member = CustomerGroupMember(
            group = this,
            account = guest,
            groupRole = CustomerGroupRole.MEMBER
        )
        customerGroupMemberRepo().save(member)

        return member
    }
}
