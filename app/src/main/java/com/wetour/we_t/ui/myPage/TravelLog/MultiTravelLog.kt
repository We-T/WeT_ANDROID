package com.wetour.we_t.ui.myPage.TravelLog

import com.wetour.we_t.network.data.MyPageTripRecord

data class MultiTravelLog(
    var title: String,
    var contents: MutableList<MyPageTripRecord>
)