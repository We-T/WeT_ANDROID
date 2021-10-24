package com.wetour.we_t.network.data

data class MypageResponse(
    val name: String,
    val type: Int,
    val profile: String,
    val my_good: ArrayList<MyGood>
)

data class MyGood(
    val title: String,
    val firstimage: String,
    val addr1: String,
    val tag_list: ArrayList<String>,
    val is_good: Int
)