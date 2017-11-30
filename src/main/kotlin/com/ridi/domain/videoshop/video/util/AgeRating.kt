package com.ridi.domain.videoshop.video.util

import com.ridi.domain.videoshop.account.exception.CustomerNotRegisterBirthException


enum class AgeRating(val displayName: String) {
    ALL("전체 관람가") {
        override fun isPassing(age: Int?): Boolean {
            return true
        }
    },
    LIMIT_12("12세 관람가") {
        override fun isPassing(age: Int?): Boolean {
            val _age = age ?: throw CustomerNotRegisterBirthException()
            return _age >= 12
        }
    },
    LIMIT_15("15세 관람가") {
        override fun isPassing(age: Int?): Boolean {
            val _age = age ?: throw CustomerNotRegisterBirthException()
            return _age >= 15
        }
    },
    LIMIT_18("청소년관림불가") {
        override fun isPassing(age: Int?): Boolean {
            val _age = age ?: throw CustomerNotRegisterBirthException()
            return _age >= 18
        }
    },
    LIMIT_ALL("제한상영가") {
        override fun isPassing(age: Int?): Boolean {
            return false
        }
    };

    abstract fun isPassing(age: Int?): Boolean
}
