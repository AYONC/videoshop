package com.ridi.domain.post

import javax.validation.constraints.*


// TODO: validation 이 안됨
data class CreatePostRequest (
    @NotBlank(message = "content is blank")
    @NotEmpty(message = "content is empty")
    @NotNull(message = "content is null")
    @Min(value = 1)
    private val content: String,
    @NotBlank(message = "user is blank")
    @NotEmpty(message = "user is empty")
    @NotNull(message = "user is null")
    @Min(value = 1)
    private val user: String
){
    fun toEntity() = Post(content = content, user = user)
}
