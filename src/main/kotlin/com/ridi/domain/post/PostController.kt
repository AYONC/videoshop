package com.ridi.domain.post

import com.ridi.common.exception.NotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostController (
    private val postService: PostService
) {
    @GetMapping("/test1")
    fun postTest1() : Any {
        postService.findTest1()?.let {
            return mapOf("post" to it)
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
}
