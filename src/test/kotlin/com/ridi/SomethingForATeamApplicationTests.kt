package com.ridi

import com.ridi.domain.member.repository.MemberRepository
import com.ridi.domain.account.repository.AccountRepository
import com.ridi.domain.task.repository.TaskRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class SomethingForATeamApplicationTests {
    @Autowired
    lateinit var taskRepo: TaskRepository
	@Autowired
	lateinit var memberRepo: MemberRepository
    @Autowired
    lateinit var accountRepo: AccountRepository
	@Test
	fun test_1() {
	}
}
