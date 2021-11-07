package com.wetour.we_t.network.data

data class MypageMyGoodListResponse(
    val my_good_list: ArrayList<GoodList>
)
data class GoodList(
    val title: String,
    val firstimage: String,
    val addr1: String,
    val tag_list: ArrayList<String>,
    val is_good: Int
)