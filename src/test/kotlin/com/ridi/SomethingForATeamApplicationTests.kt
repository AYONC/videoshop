package com.ridi

import com.ridi.common.loggerFor
import com.ridi.domain.post.PostComment
import com.ridi.domain.post.PostService
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
    lateinit var postService: PostService

    val logger = loggerFor(javaClass)

	@Test
	fun test_1() {
		val postComments = postService.findComments()
        println("###, ${postComments.size}")
        println("###, ${postComments}")
        assertNotEquals(postComments.size, 0)
	}
}
