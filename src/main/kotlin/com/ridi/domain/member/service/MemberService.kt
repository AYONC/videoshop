package com.ridi.domain.member

import com.ridi.domain.member.MemberRepository
import com.ridi.domain.task.model.Task
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepo: MemberRepository
) {
    fun create(member: Member) = memberRepo.save(member)

    fun findAll() = memberRepo.findAll()

    fun getOne(id: Long) = memberRepo.getOne(id)

    fun findAssignableMembersByTask(task: Task): List<Member> {
        return findAll().filter { it != task.member }
    }
}