package com.ridi.domain.videoshop.video.util

import org.springframework.core.convert.converter.Converter


class AgeRatingConverter: Converter<String, AgeRating> {
    override fun convert(source: String?): AgeRating {
        return AgeRating.values()[source!!.toInt()]
    }
}
