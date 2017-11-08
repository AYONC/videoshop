package com.ridi.common

enum class RoleType {
    ADMIN {
        override fun toString() = "ADMIN"
    },
    STAFF {
        override fun toString() = "USER"
    }
}
