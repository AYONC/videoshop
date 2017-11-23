package com.ridi.domain.videoshop.coin.repository

import com.ridi.domain.videoshop.coin.model.CoinUse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CoinUseRepository : JpaRepository<CoinUse, Long>
