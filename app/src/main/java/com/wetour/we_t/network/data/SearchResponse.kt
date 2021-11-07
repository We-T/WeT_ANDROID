package com.wetour.we_t.network.data

data class SearchResponse(
    val result_search_keyword: ArrayList<ResultSearchKeyword>,
    val totalCount: Int,
    val pageNo: Int
)

data class ResultSearchKeyword(
    val title: String,
    val firstimage: String,
    val addr1: String,
    val overview: Int
)