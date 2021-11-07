package com.wetour.we_t.network

import com.google.gson.JsonObject
import com.wetour.we_t.network.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RequestInterface {

    @POST("/join")
    fun requestJoin(@Body body: JsonObject): Call<JsonObject>

    @POST("/login")
    fun requestLogin(@Body body: JsonObject): Call<JsonObject>



    @POST("/mypage")
    fun requestMypage(@Body body: JsonObject): Call<MypageResponse>

    @POST("/mypage/add_family")
    fun requestFamilyList(@Body body: JsonObject): Call<JsonObject>

    @POST("/mypage/add_family_number")
    fun requestFamilyCode(@Body body: JsonObject): Call<JsonObject>

    @POST("/mypage/parents_good_list")
    fun requestMypageParentsGoodList(@Body body: JsonObject): Call<MypageParentGoodListResponse>

    @POST("/mypage/my_good_list")
    fun requestMypageMyGoodList(@Body body: JsonObject): Call<MypageMyGoodListResponse>

    @POST("/mypage/trip_record")
    fun reqeustMypageTripRecord(@Body body: JsonObject): Call<MyPageTripRecordResponse>


    @POST("/main")
    fun requestMain(@Body body: JsonObject): Call<MainResponse>

    @POST("/search/keyword")
    fun requestSearch(@Body body: JsonObject): Call<SearchResponse>

    @POST("/area/category")
    fun requestPlaceInfo(@Body body: JsonObject): Call<PlaceInfoResponse>



    @POST("/trip/save")
    fun requestMakeTour(@Body body: JsonObject): Call<JsonObject>

    @POST("/trip/select_area")
    fun requestParentLikePlace(@Body body: JsonObject): Call<ArrayList<PGood>>
}