package com.ridi.domain.post

data class PostResponse(private val post: Post) {
    val content = post.content
}
