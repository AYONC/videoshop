package com.ridi.domain.videoshop.coin.service

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.coin.repository.CoinRepository
import org.springframework.stereotype.Service

@Service
class CoinService(
    val coinRepo: CoinRepository
) {
    fun findAvailableOf(account: Account) = coinRepo.findAvailableOf(account.id)

    fun getRemainingSumOf(account: Account) = coinRepo.getRemainingSumOf(account.id)
}
