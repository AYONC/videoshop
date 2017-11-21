package com.ridi.domain.videoshop.account.exception

class CustomerGroupFullException(msg: String?) : RuntimeException(msg) {
    constructor() : this(null)
}
