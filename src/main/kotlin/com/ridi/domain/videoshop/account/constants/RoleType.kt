package com.ridi.domain.videoshop.account.constants

enum class RoleType {
    ADMIN {
        override fun toString() = "ADMIN"
    },
    STAFF {
        override fun toString() = "STAFF"
    },
    CUSTOMER {
        override fun toString() = "CUSTOMER"
    }
}
