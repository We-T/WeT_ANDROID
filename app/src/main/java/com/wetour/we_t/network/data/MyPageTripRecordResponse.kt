package com.wetour.we_t.network.data

data class MyPageTripRecordResponse(
    val trip_record_list: ArrayList<MyPageTripRecord>
)

data class MyPageTripRecord(
    val trip_name: String,
    val start_day: String,
    val end_day: String,
    val area_name: String,
    val attend_famliy: ArrayList<AttendFamily>
)