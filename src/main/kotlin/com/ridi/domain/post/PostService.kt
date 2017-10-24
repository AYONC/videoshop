package com.ridi.domain.post

import com.ridi.common.loggerFor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepo: PostRepository,
    private val postCommentRepo: PostCommentRepository
) {
    val logger = loggerFor(javaClass)

    @Transactional
    fun createPost(content: String, user: String) = postRepo.save(Post(content = content, user = user))

    @Transactional
    fun create(post: Post) = postRepo.save(post)

    @Transactional
    fun createPostComment(content: String, user: String, postId: Long) {
        val post = postRepo.getOne(postId)
        postCommentRepo.save(PostComment(content = content, user = user, post = post))
    }

    fun findAll() : List<Post> = postRepo.findAll()

    fun findTest1() : Post? = postRepo.findTest1()

    fun findTest2() : Post? = postRepo.findTest2()

    fun findTest3(user: String) : List<Post> = postRepo.findByUser(user)

    fun findComments() : List<PostComment> {
        val comments = postCommentRepo.findAll()
        logger.debug("###, comments: ${comments}")
        logger.debug("###, comments.size: ${comments.size}")
        return comments
    }
}
