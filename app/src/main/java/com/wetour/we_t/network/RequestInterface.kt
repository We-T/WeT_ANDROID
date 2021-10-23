package com.wetour.we_t.network

import com.google.gson.JsonObject
import com.wetour.we_t.network.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

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
    fun requestFamilyCode(@Body body: FamilyCodeRequest): Call<FamilyCodeResponse>

    @POST("/mypage/parents_good_list")
    fun requestMypageParentsGoodList(@Body body: JsonObject): Call<MutableList<MypageGoodListResponse>>

    @POST("/mypage/my_good_list")
    fun requestMypageMyGoodList(@Body body: JsonObject): Call<MutableList<MypageGoodListResponse>>

//    @POST("/mypage/trip_record")
//    fun reqeustMypageTripRecord(@Body body: JsonObject): Call<>
}