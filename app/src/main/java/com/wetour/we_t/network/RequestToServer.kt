package com.wetour.we_t.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RequestToServer {

//    var BASE_URL = "http://39.117.207.206:8081"
    var BASE_URL = "http://ec2-3-35-92-90.ap-northeast-2.compute.amazonaws.com:3000"
    var test = "http://172.20.10.2:3000"

//    val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    val clientBuilder = OkHttpClient.Builder().addInterceptor(loggingInterceptor)

    val gson: Gson = GsonBuilder().setLenient().create()

    var retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(clientBuilder.build())
            .build()

    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
}