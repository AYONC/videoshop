package com.ridi.domain.videoshop.coin.repository

import com.ridi.domain.videoshop.coin.model.Coin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CoinRepository : JpaRepository<Coin, Long> {
    @Query("select c from Coin c WHERE account_id = :accountId AND remaining_quantity <> 0")
    fun findAvailableOf(accountId: Long): List<Coin>

    @Query("select sum(c.remainingQuantity) from Coin c WHERE account_id = :accountId AND remaining_quantity <> 0")
    fun getRemainingSumOf(accountId: Long): Long
}
