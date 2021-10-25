package com.wetour.we_t.data

data class MultiRecyclerData(
    val type: Int,
    val place: String,
    val image: String,
    val hashTag: ArrayList<String>?
) {
    companion object {
        const val BASIC_TYPE = 0
        const val HASHTAG_TYPE = 1
        const val HASHTAG_WIDE_TYPE = 2
        const val FESTIVAL_TYPE = 3
    }
}