package com.wetour.we_t.data

data class PlaceInfoData(
    val image: String,
    var heart: Boolean,
    val placeName: String,
    val placeAddress: String,
    val star: Int,
    val hashTag: ArrayList<String>?
)