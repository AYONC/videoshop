package com.ridi.common

import com.ridi.domain.videoshop.account.constants.RoleType
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.account.model.Privilege
import com.ridi.domain.videoshop.account.repository.PrivilegeRepository
import com.ridi.domain.videoshop.coin.model.Coin
import com.ridi.domain.videoshop.video.model.Video
import com.ridi.domain.videoshop.video.model.VideoPrice
import com.ridi.domain.videoshop.video.util.AgeRating
import com.ridi.domain.videoshop.videorent.model.VideoRent
import com.ridi.domain.videoshop.videorent.model.VideoRentOrder
import java.util.*

fun dummyAccount(
    username: String = "test_username",
    name: String = "test_name",
    password: String = "test_password",
    phone: String = "test_phone",
    birth: Date? = null
) = Account(
    username = username,
    name = name,
    password = password,
    phone = phone,
    birth = birth
)

fun dummyPrivilege(
    name: String = "test_name",
    codename: String = "test_codename"
) = Privilege(
    name = name,
    codename = codename
)

fun dummyVideo(
    title: String = "test_title",
    description: String = "test_description",
    rating: AgeRating = AgeRating.ALL,
    coverPath: String? = null,
    isOpened: Boolean = false
) = Video(
    title = title,
    description = description,
    rating = rating,
    coverPath = coverPath,
    isOpened = isOpened
)

fun dummyVideoPrice(
    video: Video = dummyVideo(),
    price: Int = 0,
    isActive: Boolean = false
) = VideoPrice(
    video = video,
    price = price,
    isActive = isActive
)

fun dummyVideoRentOrder(
    video: Video = dummyVideo(),
    videoRent: VideoRent? = null,
    rentDays: Int = 3,
    isProvided: Boolean = false
) = VideoRentOrder(
    video = video,
    videoRent = videoRent,
    rentDays = rentDays,
    isProvided = isProvided
)

fun dummyVideoRent(
    account: Account = dummyAccount(),
    video: Video = dummyVideo(),
    expireDate: Date = Date()
) = VideoRent(
    account = account,
    video = video,
    expireDate = expireDate
)

fun dummyCoin(
    account: Account = dummyAccount(),
    amount: Long = 0L,
    remainingQuantity: Long = 0L
) = Coin(
    account = account,
    amount = amount,
    remainingQuantity = remainingQuantity
)

fun initializePrivilege() {
    val privilegeRepo = SpringBeanHelper.getBean(PrivilegeRepository::class.java)

    val codeNames = privilegeRepo.findAll().map { it.codename }
    val newPrivileges = RoleType.values().filter {
        it.toString() !in codeNames
    }

    newPrivileges.forEach {
        privilegeRepo.save(Privilege(name = it.toString(), codename = it.toString()))
    }
}
