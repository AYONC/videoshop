package com.ridi.domain.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Long>, PostRepositoryJooq {
    fun findByUser(user: String): List<Post>
}
