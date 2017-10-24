package com.ridi.domain.post

import com.ridi.common.exception.NotFoundException
import com.ridi.common.loggerFor
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/post")
class PostController (
    private val postService: PostService
) {
    val logger = loggerFor(javaClass)

    @PostMapping("/")
    fun createPost(@RequestBody @Valid req: CreatePostRequest, bindingResult: BindingResult) = mapOf(
        "bindingResult" to bindingResult.hasErrors(),
        "post" to postService.create(req.toEntity())
    )

    @GetMapping("create-post-comment/{content}/{user}/{postId}")
    fun createPostComment(@PathVariable content: String, @PathVariable user: String, @PathVariable postId: Long): Any {
        postService.createPostComment(content, user, postId)
        return mapOf("success" to true)
    }

    @GetMapping("/test1")
    fun postTest1() : Any {
        postService.findTest1()?.let {
            return mapOf(
                "post" to it
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
        "posts" to postService.findTest3(user).map { PostResponse(it) }
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
