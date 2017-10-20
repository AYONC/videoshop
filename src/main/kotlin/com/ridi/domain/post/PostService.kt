package com.ridi.domain.post

import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepo: PostRepository
) {
    fun findTest1() : Post? = postRepo.findTest1()
    fun findTest2() : Post? = postRepo.findTest2()
    fun findTest3(user: String) : List<Post> = postRepo.findByUser(user)
}
