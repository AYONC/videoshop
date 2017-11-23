package com.ridi.domain.videoshop.videorent.model

import com.ridi.common.EntityListener
import com.ridi.common.SpringBeanHelper
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.coin.service.CoinUseService
import com.ridi.domain.videoshop.video.model.Video
import com.ridi.domain.videoshop.videorent.exception.VideoRentOrderAlreadyProvidedException
import com.ridi.domain.videoshop.videorent.repository.VideoRentOrderRepository
import com.ridi.domain.videoshop.videorent.repository.VideoRentRepository
import org.joda.time.DateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(EntityListener::class)
@Table(name = "video_rent_order")
data class VideoRentOrder(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "video_id")
    var video: Video,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "video_rent_id")
    var videoRent: VideoRent? = null,

    @Column(name = "rent_days") @NotNull val rentDays: Int = 0,
    @Column(name = "is_provided") @NotNull var isProvided: Boolean = false,
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
) {
    private fun coinUseService() = SpringBeanHelper.getBean(CoinUseService::class.java)
    private fun videoRentRepo() = SpringBeanHelper.getBean(VideoRentRepository::class.java)
    private fun videoRentOrderRepo() = SpringBeanHelper.getBean(VideoRentOrderRepository::class.java)

    internal fun getExpireDate() = DateTime()
        .plusDays(rentDays - 1)
        .withHourOfDay(23)
        .withMinuteOfHour(59)
        .withSecondOfMinute(59)
        .withMillisOfSecond(99)
        .toDate()

    fun provideFor(account: Account): VideoRent {
        if (isProvided) {
            throw VideoRentOrderAlreadyProvidedException()
        }

        coinUseService().useFor(this, account)

        val videoRent = VideoRent(
            account = account,
            video = video,
            expireDate = getExpireDate()
        )
        videoRentRepo().save(videoRent)

        this.isProvided = true
        this.videoRent = videoRent
        videoRentOrderRepo().save(this)

        return videoRent
    }
}