package com.wetour.we_t.network.data

data class MypageGoodListResponse(
    val p_good_list: ArrayList<PGoodList>
)
data class PGoodList(
    val title: String,
    val firstimage: String,
    val addr1: String,
    val tag_list: ArrayList<String>,
    val is_good: Int
)