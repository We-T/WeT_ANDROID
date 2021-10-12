package com.wetour.we_t.ui.myPage.TravelLog

import com.wetour.we_t.data.TravelLogData

data class MultiTravleLog(
    var title: String,
    var contents: List<TravelLogData>
)