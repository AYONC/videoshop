package com.ridi.domain.videoshop.account.exception

class CustomerNotRegisterBirthException(msg: String?) : RuntimeException(msg) {
    constructor() : this(null)
}