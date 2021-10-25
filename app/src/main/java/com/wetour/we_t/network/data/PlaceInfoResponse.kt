package com.wetour.we_t.network.data

data class PlaceInfoResponse(
    val p_good_area: ArrayList<PGoodArea>
)

data class PGoodArea(
    val title: String,
    val tag_list: ArrayList<String>,
    val addr1: String,
    val firstimage: String,
    val is_good: Int,
    val score: Float
)