package com.ridi.domain.videoshop.coin.model

import com.ridi.common.EntityListener
import com.ridi.domain.videoshop.videorent.model.VideoRentOrder
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(EntityListener::class)
@Table(name = "coin_use")
data class CoinUse(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coin_id")
    var coin: Coin? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    var order: VideoRentOrder? = null,

    @Column(name = "quantity") @NotNull val quantity: Long = 0,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
)