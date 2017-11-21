package com.ridi.domain.videoshop.account.exception

class CustomerInGroupException(msg: String?) : RuntimeException(msg) {
    constructor() : this(null)
}