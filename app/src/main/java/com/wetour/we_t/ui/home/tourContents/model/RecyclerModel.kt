package com.wetour.we_t.ui.home.tourContents.model

data class RecyclerModel(val type: Int, val place: String, val image: Int, val hashTag: ArrayList<String>?) {
    companion object {
        const val BASIC_TYPE = 0
        const val HASHTAG_TYPE = 1
        const val HASHTAG_WIDE_TYPE= 2
        const val FESTIVAL_TYPE = 3
    }
}