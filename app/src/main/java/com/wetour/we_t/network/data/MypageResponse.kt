package com.wetour.we_t.network.data

data class MypageResponse(
    val name: String,
    val type: Int,
    val title: String,
    val firstimage: String,
    val addr1: String,
    val overview: String,
    val is_good: Int
)