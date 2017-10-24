package com.ridi.domain.post

import com.ridi.common.exception.NotFoundException
import com.ridi.common.loggerFor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostController (
    private val postService: PostService
) {
    val logger = loggerFor(javaClass)

    @GetMapping("create-post/{content}/{user}")
    fun createPost(@PathVariable content: String, @PathVariable user: String): Any {
        return mapOf(
            "posts" to postService.createPost(content, user)
        )
    }

    @GetMapping("create-post-comment/{content}/{user}/{postId}")
    fun createPostComment(@PathVariable content: String, @PathVariable user: String, @PathVariable postId: Long): Any {
        postService.createPostComment(content, user, postId)
        return mapOf("success" to true)
    }

    @GetMapping("/test1")
    fun postTest1() : Any {
        postService.findTest1()?.let {
            return mapOf(
                "post" to it,
                "comments" to postService.findComments()
            )
        }
        throw NotFoundException()
    }

    @GetMapping("/test2")
    fun postTest2() : Any {
        postService.findTest2()?.let {
            return mapOf("post" to it)
        }
        throw NotFoundException()
    }

    @GetMapping("/test3/{user}")
    fun postTest3(@PathVariable user: String) = mapOf(
        "posts" to postService.findTest3(user)
    )

    @GetMapping("/comment/test1")
    fun commentTest1() = mapOf(
        "commentssss" to postService.findComments()
    )

    @GetMapping("/test4")
    fun postTest4(): Any {
        return "testttt"
    }
}
