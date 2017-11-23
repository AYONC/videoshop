package com.ridi.domain.videoshop.coin.model

import com.ridi.common.EntityListener
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.coin.exception.NotEnoughCoinException
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(EntityListener::class)
@Table(name = "coin")
data class Coin(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    var account: Account? = null,

    @Column(name = "amount") @NotNull val amount: Long = 0,
    @Column(name = "remaining_quantity") @NotNull var remainingQuantity: Long = 0,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
) {
    private fun assertHasEnoughQuantity(requiredQuantity: Long) {
        if (requiredQuantity > remainingQuantity) {
            throw NotEnoughCoinException()
        }
    }

    fun useFor(quantity: Long) {
        assertHasEnoughQuantity(quantity)
        this.remainingQuantity = remainingQuantity - quantity
    }
}