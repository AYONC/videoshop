package com.ridi.common.dto

interface PasswordMatchable {
    fun password(): String
    fun matchPassword(): String
}