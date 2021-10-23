package com.wetour.we_t.network.data

data class MainResponse(
    val name: String,
    val profile: String,
    val type: String,
    val num_fam: String,
    val wet_good: ArrayList<WetGood>,
    val tv_tour: ArrayList<TvTour>,
    val festival: ArrayList<Festival>,
    val p_good: ArrayList<PGood>?,
    val trip_record_list: ArrayList<TripRecordList>?
)

data class WetGood(
    val title: String,
    val firstimage: String,
    val tag_list: ArrayList<String>?
)

data class TvTour(
    val firstimage: String,
    val addr1: String,
    val tag_list: ArrayList<String>?
)

data class Festival(
    val title: String,
    val firstimage: String,
    val addr1: String
)

data class PGood(
    val title: String,
    val firstimage: String
)

data class TripRecordList(
    val trip_name: String,
    val start_day: String,
    val end_day: String,
    val attend_famliy: ArrayList<AttendFamily>
)

data class AttendFamily(
    val profile: String
)