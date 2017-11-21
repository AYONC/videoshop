package com.ridi.domain.videoshop.account.service

import com.ridi.domain.videoshop.account.constants.CustomerGroupRole
import com.ridi.domain.videoshop.account.dto.AddGroupRequest
import com.ridi.domain.videoshop.account.dto.InviteToGroupRequest
import com.ridi.domain.videoshop.account.exception.CustomerInGroupException
import com.ridi.domain.videoshop.account.exception.InvalidLeaderException
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.CustomerGroup
import com.ridi.domain.videoshop.account.model.CustomerGroupMember
import com.ridi.domain.videoshop.account.repository.CustomerGroupMemberRepository
import com.ridi.domain.videoshop.account.repository.CustomerGroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CustomerGroupService {
    @Autowired lateinit var customerService: CustomerService
    @Autowired lateinit var customerGroupRepo: CustomerGroupRepository
    @Autowired lateinit var customerGroupMemberRepo: CustomerGroupMemberRepository

    @Transactional
    fun createGroup(req: AddGroupRequest, leader: Account): CustomerGroup {
        leader.assertIsCustomer()
        assertIsNotInGroup(leader)

        val group = CustomerGroup(title = req.title)
        customerGroupRepo.save(group)

        val member = CustomerGroupMember(
            group = group,
            account = leader,
            groupRole = CustomerGroupRole.LEADER
        )
        customerGroupMemberRepo.save(member)

        return group
    }

    fun inviteToGroup(req: InviteToGroupRequest, leader: Account): CustomerGroupMember {
        val guest = customerService.getOne(req.guestAccountId)

        leader.assertIsCustomer()
        guest.assertIsCustomer()
        assertIsNotInGroup(guest)

        val group: CustomerGroup
        try {
            group = getByLeader(leader)
        } catch(e: EmptyResultDataAccessException) {
            throw InvalidLeaderException(e)
        }

        return group.invite(guest)
    }

    private fun isInGroup(customer: Account) = customerGroupMemberRepo.countByAccount(customer) > 0

    private fun assertIsNotInGroup(customer: Account) {
        if (isInGroup(customer)) {
            throw CustomerInGroupException()
        }
    }

    @Throws(EmptyResultDataAccessException::class)
    fun getByLeader(leader: Account): CustomerGroup {
        val groupMember = customerGroupMemberRepo.getByAccountAndGroupRole(leader, CustomerGroupRole.LEADER)
        return groupMember.group
    }
}
