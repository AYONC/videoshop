package com.ridi

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class SomethingForATeamApplicationTests {
//    @Autowired
//    lateinit var taskRepo: TaskRepository
//	@Autowired
//	lateinit var memberRepo: MemberRepository
//    @Autowired
//    lateinit var accountRepo: AccountRepository

	@Test
    fun accountFindByUsername() {
//        val account = accountRepo.findByUsername("test")
//        print(account)
    }

    @Test
    fun taskFind() {
        assertTrue(true)
//        val tasks = taskRepo.findAssigned(1)
//        print(tasks)
	}
}
