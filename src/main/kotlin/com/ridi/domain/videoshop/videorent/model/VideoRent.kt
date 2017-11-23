package com.ridi.domain.videoshop.videorent.model

import com.ridi.common.EntityListener
import com.ridi.domain.videoshop.account.model.Account
import com.ridi.domain.videoshop.video.model.Video
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@EntityListeners(EntityListener::class)
@Table(name = "video_rent")
data class VideoRent(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    var account: Account? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "video_id")
    var video: Video? = null,

    @Column(name = "expire_date") @NotNull val expireDate: Date = Date(),
    @Column(name = "created_at") @NotNull val createdAt: Date = Date()
)
