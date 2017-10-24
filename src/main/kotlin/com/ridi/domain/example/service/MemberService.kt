package com.ridi.domain.example.service

import com.ridi.domain.example.model.Member
import com.ridi.domain.example.model.Task
import com.ridi.domain.example.repository.MemberRepository
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