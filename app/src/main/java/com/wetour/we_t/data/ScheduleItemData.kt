package com.wetour.we_t.data

data class ScheduleItemData(
    val number: Int,
    val distance: String,
    val item_location: String,
    val item_distance: String,
    val item_kind: String,
    val item_runningTime: String?,
    val item_congestion: String?,
    val item_memo: String?
)