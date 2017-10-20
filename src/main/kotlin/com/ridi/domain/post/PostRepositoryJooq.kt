package com.ridi.domain.post

import org.springframework.stereotype.Repository

@Repository
interface PostRepositoryJooq {
    fun findTest1(): Post?
    fun findTest2(): Post?
}
