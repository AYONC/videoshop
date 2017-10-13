package com.ridi.domain.post

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/post")
class PostController (
    private val postService: PostService
) {
    @GetMapping("/test1")
    fun postTest1(): Post = postService.findTest1()

    @GetMapping("/test2")
    fun postTest2(): Post = postService.findTest2()

    @GetMapping("/test3/{user}")
    fun postTest3(@PathVariable user: String) = postService.findTest3(user)
}
