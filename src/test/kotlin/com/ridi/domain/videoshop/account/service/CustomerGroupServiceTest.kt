package com.ridi.domain.videoshop.account.service

import com.ridi.common.dummyAccount
import com.ridi.common.initializePrivilege
import com.ridi.domain.videoshop.account.dto.AddGroupRequest
import com.ridi.domain.videoshop.account.dto.InviteToGroupRequest
import com.ridi.domain.videoshop.account.exception.CustomerAssertionFailedException
import com.ridi.domain.videoshop.account.exception.CustomerGroupFullException
import com.ridi.domain.videoshop.account.exception.CustomerInGroupException
import com.ridi.domain.videoshop.account.repository.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class CustomerGroupServiceTest {
    @Autowired lateinit var accountRepo: AccountRepository
    @Autowired lateinit var customerService: CustomerService
    @Autowired lateinit var staffService: StaffService
    @Autowired lateinit var customerGroupRepo: CustomerGroupRepository
    @Autowired lateinit var customerGroupMemberRepo: CustomerGroupMemberRepository
    @Autowired lateinit var customerGroupService: CustomerGroupService
    @Autowired lateinit var accountRoleRepo: AccountRoleRepository
    @Autowired lateinit var privilegeRepo: PrivilegeRepository

    @Test
    fun givenCustomer_when그룹_생성_then성공() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val group = customerGroupService.createGroup(AddGroupRequest("group_title"), account)
        assertNotNull(group)
    }

    @Test(expected = CustomerAssertionFailedException::class)
    fun givenCustomer가_아닌_계정_when그룹_생성_then실패() {
        var account = dummyAccount()
        staffService.createAsStaff(account)
        account = accountRepo.getOne(account.id)

        customerGroupService.createGroup(AddGroupRequest("group_title"), account)
    }

    @Test
    fun givenCustomer가_when그룹에_초대받으면_then성공() {
        var leader = dummyAccount()
        var guest = dummyAccount()

        customerService.createAsCustomer(leader)
        customerService.createAsCustomer(guest)

        leader = accountRepo.getOne(leader.id)
        guest = accountRepo.getOne(guest.id)

        val group = customerGroupService.createGroup(AddGroupRequest("group_title"), leader)
        assertNotNull(group)

        val member = customerGroupService.inviteToGroup(InviteToGroupRequest(guest.id), leader)
        assertNotNull(member)

        assertEquals(group.getMemberCount(), 2)
    }

    @Test(expected = CustomerAssertionFailedException::class)
    fun givenCustomer가_아닌_계정이_when그룹에_초대받으면_then실패() {
        var leader = dummyAccount()
        var guest = dummyAccount()

        customerService.createAsCustomer(leader)
        staffService.createAsStaff(guest)

        leader = accountRepo.getOne(leader.id)
        guest = accountRepo.getOne(guest.id)

        val group = customerGroupService.createGroup(AddGroupRequest("group_title"), leader)
        assertNotNull(group)

        customerGroupService.inviteToGroup(InviteToGroupRequest(guest.id), leader)
    }

    @Test(expected = CustomerInGroupException::class)
    fun givenLeader가_when그룹에_초대받으면_then실패() {
        var leader = dummyAccount()
        var anotherLeader = dummyAccount()

        customerService.createAsCustomer(leader)
        customerService.createAsCustomer(anotherLeader)

        leader = accountRepo.getOne(leader.id)
        anotherLeader = accountRepo.getOne(anotherLeader.id)

        val group = customerGroupService.createGroup(AddGroupRequest("group"), leader)
        assertNotNull(group)

        val anotherGroup = customerGroupService.createGroup(AddGroupRequest("another_group"), anotherLeader)
        assertNotNull(anotherGroup)

        customerGroupService.inviteToGroup(InviteToGroupRequest(leader.id), anotherLeader)
    }

    @Test(expected = CustomerInGroupException::class)
    fun givenMember가_when그룹에_초대받으면_then실패() {
        var leader = dummyAccount()
        var anotherLeader = dummyAccount()
        var guest = dummyAccount()

        customerService.createAsCustomer(leader)
        customerService.createAsCustomer(anotherLeader)
        customerService.createAsCustomer(guest)

        leader = accountRepo.getOne(leader.id)
        anotherLeader = accountRepo.getOne(anotherLeader.id)
        guest = accountRepo.getOne(guest.id)

        val group = customerGroupService.createGroup(AddGroupRequest("group_title"), leader)
        assertNotNull(group)

        val anotherGroup = customerGroupService.createGroup(AddGroupRequest("another_group_title"), anotherLeader)
        assertNotNull(anotherGroup)

        val member = customerGroupService.inviteToGroup(InviteToGroupRequest(guest.id), leader)
        assertNotNull(member)

        customerGroupService.inviteToGroup(InviteToGroupRequest(guest.id), anotherLeader)
    }

    @Test(expected = CustomerGroupFullException::class)
    fun when그룹_구성원수_20명_이상_then실패() {
        var leader = dummyAccount()
        customerService.createAsCustomer(leader)
        leader = accountRepo.getOne(leader.id)

        val group = customerGroupService.createGroup(AddGroupRequest("group_title"), leader)

        for (i in 1..30) {
            var guest = dummyAccount()
            customerService.createAsCustomer(guest)
            guest = accountRepo.getOne(guest.id)
            customerGroupService.inviteToGroup(InviteToGroupRequest(guest.id), leader)
        }
    }

    @Before
    fun setUp() {
        initializePrivilege()
    }
}