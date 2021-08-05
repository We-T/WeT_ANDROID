package com.wetour.we_t.data

data class PlaceInfoData(
    val image: String,
    val heart: Boolean,
    val placeName: String,
    val placeAddress: String,
    val start: Int,
    val hashTag: ArrayList<String>
)