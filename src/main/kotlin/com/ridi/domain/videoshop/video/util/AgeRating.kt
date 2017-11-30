package com.ridi.domain.videoshop.video.util


//<option value="0">전체 관람가</option>
//<option value="1">12세 관람가</option>
//<option value="2">15세 관람가</option>
//<option value="3">청소년관림불가</option>
//<option value="4">제한상영가</option>
enum class AgeRating(val displayName: String) {
    ALL("전체 관람가"),
    LIMIT_12("12세 관람가"),
    LIMIT_15("15세 관람가"),
    LIMIT_18("청소년관림불가"),
    LIMIT_ALL("제한상영가")
}
