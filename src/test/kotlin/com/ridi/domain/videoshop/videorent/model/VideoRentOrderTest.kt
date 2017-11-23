package com.ridi.domain.videoshop.videorent.model

import com.ridi.common.*
import com.ridi.domain.videoshop.account.repository.AccountRepository
import com.ridi.domain.videoshop.account.service.CustomerService
import com.ridi.domain.videoshop.coin.exception.NotEnoughCoinException
import com.ridi.domain.videoshop.coin.repository.CoinRepository
import com.ridi.domain.videoshop.coin.service.CoinService
import com.ridi.domain.videoshop.video.repository.VideoPriceRepository
import com.ridi.domain.videoshop.video.repository.VideoRepository
import com.ridi.domain.videoshop.videorent.exception.VideoRentOrderAlreadyProvidedException
import com.ridi.domain.videoshop.videorent.repository.VideoRentOrderRepository
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class VideoRentOrderTest {
    @Autowired lateinit var customerService: CustomerService
    @Autowired lateinit var accountRepo: AccountRepository
    @Autowired lateinit var videoRepo: VideoRepository
    @Autowired lateinit var videoRentOrderRepo: VideoRentOrderRepository
    @Autowired lateinit var videoPriceRepo: VideoPriceRepository
    @Autowired lateinit var coinRepo: CoinRepository
    @Autowired lateinit var coinService: CoinService

    @Test
    fun test_getExpireDate() {
        val order = dummyVideoRentOrder(rentDays = 3)
        val expireDate = DateTime(order.getExpireDate())
        assertEquals(expireDate.dayOfMonth, DateTime(Date().time + (2 * 24 * 60 * 60 * 1000)).dayOfMonth)
        assertEquals(expireDate.hourOfDay, 23)
        assertEquals(expireDate.minuteOfHour, 59)
        assertEquals(expireDate.secondOfMinute, 59)
        assertEquals(expireDate.millisOfSecond, 99)
    }

    @Test(expected = VideoRentOrderAlreadyProvidedException::class)
    fun given_이미_provide된_order_when_provide_시도_then_실패() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val order = dummyVideoRentOrder(isProvided = true)
        order.provideFor(account)
    }

    @Test(expected = NotEnoughCoinException::class)
    fun given_코인_충분하지_않은_계정_when_provide_시도_then_실패() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val video = dummyVideo(isOpened = true)
        videoRepo.save(video)

        val videoPrice = dummyVideoPrice(video = video, price = 1000, isActive = true)
        videoPriceRepo.save(videoPrice)

        val order = dummyVideoRentOrder(video = video)
        order.provideFor(account)
    }

    @Test
    fun given_무료비디오_when_provide_시도_then_성공() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val video = dummyVideo(isOpened = true)
        videoRepo.save(video)

        val videoPrice = dummyVideoPrice(video = video, price = 0, isActive = true)
        videoPriceRepo.save(videoPrice)

        val order = dummyVideoRentOrder(video = video)
        videoRentOrderRepo.save(order)

        val rent = order.provideFor(account)

        assertNotNull(rent)
    }

    @Test
    fun given_유료비디오_when_provide_시도_then_성공() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        (1..3).forEach {
            val coin = dummyCoin(account = account, amount = 400, remainingQuantity = 400)
            coinRepo.save(coin)
        }

        val video = dummyVideo(isOpened = true)
        videoRepo.save(video)

        val videoPrice = dummyVideoPrice(video = video, price = 1000, isActive = true)
        videoPriceRepo.save(videoPrice)

        val order = dummyVideoRentOrder(video = video)
        videoRentOrderRepo.save(order)

        val rent = order.provideFor(account)

        assertNotNull(rent)

        assertEquals(coinService.getRemainingSumOf(account), 200)
    }

    @Before
    fun setUp() {
        initializePrivilege()
    }
}