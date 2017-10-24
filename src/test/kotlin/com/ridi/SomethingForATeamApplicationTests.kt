package com.ridi

import com.ridi.domain.example.member.MemberService
import com.ridi.domain.example.task.TaskService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import org.junit.Assert.*

@RunWith(SpringRunner::class)
@SpringBootTest
class SomethingForATeamApplicationTests {
    @Autowired
    lateinit var memberService: MemberService
    @Autowired
    lateinit var taskService: TaskService

	@Test
	fun test_1() {
	}
}
