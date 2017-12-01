package com.ridi.domain.videoshop.coin.service

import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.coin.dto.CoinUsable
import com.ridi.domain.videoshop.coin.exception.NotEnoughCoinException
import com.ridi.domain.videoshop.coin.model.CoinUse
import com.ridi.domain.videoshop.coin.repository.CoinRepository
import com.ridi.domain.videoshop.coin.repository.CoinUseRepository
import com.ridi.domain.videoshop.video.service.VideoPriceService
import com.ridi.domain.videoshop.videorent.model.VideoRentOrder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CoinUseService(
    val videoPriceService: VideoPriceService,
    val coinService: CoinService,
    val coinRepo: CoinRepository,
    val coinUseRepo: CoinUseRepository
) {
    @Transactional
    fun useFor(order: VideoRentOrder, account: Account) {
        val price = videoPriceService.getLatestByVideo(order.video).price
        val coinUsables = findCoinUsable(account, price.toLong())

        coinUsables.forEach {
            val coinUse = CoinUse(
                coin = it.coin,
                order = order,
                quantity = it.quantity
            )

            it.coin.useFor(it.quantity)

            coinUseRepo.save(coinUse)
            coinRepo.save(it.coin)
        }
    }

    fun findCoinUsable(account: Account, quantity: Long): List<CoinUsable> {
        val coins = coinService.findAvailableOf(account)
        val coinSum = coins.map { it.remainingQuantity }.sum()

        if (coinSum < quantity) {
            throw NotEnoughCoinException()
        }

        val coinUsables = mutableListOf<CoinUsable>()
        var totalRequiredQuantity = quantity
        for (coin in coins) {
            if (totalRequiredQuantity <= 0) {
                break
            }

            val requiredQuantity = if (coin.remainingQuantity > totalRequiredQuantity) {
                totalRequiredQuantity
            } else {
                coin.remainingQuantity
            }

            totalRequiredQuantity -= requiredQuantity

            coinUsables + CoinUsable(coin, requiredQuantity) // 새로운 list를 반환

            coinUsables.add(CoinUsable(coin, requiredQuantity))
        }

        return coinUsables
    }
}
