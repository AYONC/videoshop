package com.ridi.domain.videoshop.videorent.service

import com.ridi.common.*
import com.ridi.domain.videoshop.account.exception.CustomerAssertionFailedException
import com.ridi.domain.videoshop.account.exception.CustomerNotRegisterBirthException
import com.ridi.domain.videoshop.account.repository.AccountRepository
import com.ridi.domain.videoshop.account.service.CustomerService
import com.ridi.domain.videoshop.account.service.StaffService
import com.ridi.domain.videoshop.coin.exception.NotEnoughCoinException
import com.ridi.domain.videoshop.coin.repository.CoinRepository
import com.ridi.domain.videoshop.coin.service.CoinService
import com.ridi.domain.videoshop.video.exception.VideoNotOpenedException
import com.ridi.domain.videoshop.video.exception.VideoNotPassAgeRatingException
import com.ridi.domain.videoshop.video.repository.VideoPriceRepository
import com.ridi.domain.videoshop.video.repository.VideoRepository
import com.ridi.domain.videoshop.video.util.AgeRating
import com.ridi.domain.videoshop.videorent.dto.RentVideoRequest
import com.ridi.domain.videoshop.videorent.exception.VideoHasRentedBeforeException
import com.ridi.domain.videoshop.videorent.repository.VideoRentRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class VideoRentServiceTest {
    @Autowired lateinit var customerService: CustomerService
    @Autowired lateinit var staffService: StaffService
    @Autowired lateinit var accountRepo: AccountRepository
    @Autowired lateinit var coinRepo: CoinRepository
    @Autowired lateinit var coinService: CoinService
    @Autowired lateinit var videoRentService: VideoRentService
    @Autowired lateinit var videoRepo: VideoRepository
    @Autowired lateinit var videoRentRepo: VideoRentRepository
    @Autowired lateinit var videoPriceRepo: VideoPriceRepository

    @Test(expected = CustomerAssertionFailedException::class)
    fun given_customer가_아닌_계정_when_대여_시도_then_실패() {
        var account = dummyAccount()
        staffService.createAsStaff(account)
        account = accountRepo.getOne(account.id)

        videoRentService.rent(RentVideoRequest(0), account)
    }

    @Test(expected = JpaObjectRetrievalFailureException::class)
    fun given_존재하지_않는_비디오_when_대여_시도_then_실패() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val notExistVideoId = 99999L
        videoRentService.rent(RentVideoRequest(notExistVideoId), account)
    }

    @Test(expected = VideoHasRentedBeforeException::class)
    fun given_빌린적있는_비디오_when_대여_시도_then_실패() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val video = dummyVideo(isOpened = true)
        videoRepo.save(video)

        val videoRent = dummyVideoRent(account = account, video = video)
        videoRentRepo.save(videoRent)

        videoRentService.rent(RentVideoRequest(video.id), account)
    }

    @Test(expected = VideoNotOpenedException::class)
    fun given_open되지_않은_비디오_when_대여_시도_then_실패() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val video = dummyVideo(isOpened = false)
        videoRepo.save(video)

        videoRentService.rent(RentVideoRequest(video.id), account)
    }

    @Test
    fun given_무료_비디오_when_대여_시도_then_성공() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val video = dummyVideo(isOpened = true)
        videoRepo.save(video)

        val videoPrice = dummyVideoPrice(
            video = video,
            price = 0,
            isActive = true
        )
        videoPriceRepo.save(videoPrice)

        val rent = videoRentService.rent(RentVideoRequest(video.id), account)

        assertNotNull(rent)
        assertEquals(rent.account, account)
        assertEquals(rent.video?.id, video.id)
        assertTrue(rent.expireDate > Date())
    }

    @Test(expected = NotEnoughCoinException::class)
    fun given_코인없는_계정_when_비디오_대여_시도_then_실패() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val video = dummyVideo(isOpened = true)
        videoRepo.save(video)

        val videoPrice = dummyVideoPrice(
            video = video,
            price = 1500,
            isActive = true
        )
        videoPriceRepo.save(videoPrice)

        videoRentService.rent(RentVideoRequest(video.id), account)
    }

    @Test(expected = NotEnoughCoinException::class)
    fun given_코인_모자란_계정_when_비디오_대여_시도_then_실패() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        (1..3).forEach {
            val coin = dummyCoin(account = account, amount = 400, remainingQuantity = 400)
            coinRepo.save(coin)
        }

        val video = dummyVideo(isOpened = true)
        videoRepo.save(video)

        val videoPrice = dummyVideoPrice(
            video = video,
            price = 1500,
            isActive = true
        )
        videoPriceRepo.save(videoPrice)

        try {
            videoRentService.rent(RentVideoRequest(video.id), account)
        } finally {
            val coinSum = coinService.getRemainingSumOf(account)
            assertEquals(coinSum, 1200)
        }
    }

    @Test
    fun when_비디오_대여_then_성공() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        var coin = dummyCoin(
            account = account,
            amount = 2000,
            remainingQuantity = 2000
        )
        coinRepo.save(coin)

        val video = dummyVideo(isOpened = true)
        videoRepo.save(video)

        val videoPrice = dummyVideoPrice(
            video = video,
            price = 1500,
            isActive = true
        )
        videoPriceRepo.save(videoPrice)

        val rent = videoRentService.rent(RentVideoRequest(video.id), account)

        assertNotNull(rent)
        assertEquals(rent.account, account)
        assertEquals(rent.video?.id, video.id)
        assertTrue(rent.expireDate > Date())

        coin = coinRepo.getOne(coin.id)
        assertEquals(coin.remainingQuantity, 500)
    }

    @Test(expected = CustomerNotRegisterBirthException::class)
    fun given_생일_등록이_안됀_계정_when_비디오_대여_then_실패() {
        var account = dummyAccount()
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val video = dummyVideo(rating = AgeRating.LIMIT_18 ,isOpened = true)
        videoRepo.save(video)

        val videoPrice = dummyVideoPrice(
                video = video,
                price = 1500,
                isActive = true
        )
        videoPriceRepo.save(videoPrice)

        videoRentService.rent(RentVideoRequest(video.id), account)
    }

    @Test(expected = VideoNotPassAgeRatingException::class)
    fun given_생일_등록한_계정_when_비디오_대여_then_나이_미달_실패() {
        var account = dummyAccount(birth=Date())
        customerService.createAsCustomer(account)
        account = accountRepo.getOne(account.id)

        val video = dummyVideo(rating = AgeRating.LIMIT_18 ,isOpened = true)
        videoRepo.save(video)

        val videoPrice = dummyVideoPrice(
                video = video,
                price = 1500,
                isActive = true
        )
        videoPriceRepo.save(videoPrice)

        videoRentService.rent(RentVideoRequest(video.id), account)
    }

   @Before
    fun setUp() {
        initializePrivilege()
    }
}