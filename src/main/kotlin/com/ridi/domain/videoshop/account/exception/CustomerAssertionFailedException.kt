package com.ridi.domain.videoshop.account.exception

class CustomerAssertionFailedException(msg: String?) : RuntimeException(msg) {
    constructor() : this(null)
}