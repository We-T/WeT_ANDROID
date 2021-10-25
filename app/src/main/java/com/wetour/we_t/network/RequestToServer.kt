package com.wetour.we_t.network
//
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object RequestToServer {
//
//    var BASE_URL = "http://39.117.207.206:8081"
////    var test = "http://10.0.2.2:9872/"
//
//    private val loggingInterceptor: HttpLoggingInterceptor =
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    private val clientBuilder: OkHttpClient.Builder =
//        OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
////            .callTimeout(10, TimeUnit.MINUTES)
////            .connectTimeout(100, TimeUnit.SECONDS)
////            .readTimeout(100, TimeUnit.SECONDS)
////            .writeTimeout(100, TimeUnit.SECONDS)
//
//    private val gson: Gson = GsonBuilder().setLenient().create()
//
//    var retrofit: Retrofit =
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
////            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(clientBuilder.build())
//            .build()
//
//    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
//}

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RequestToServer {

    var BASE_URL = "http://39.117.207.206:8081"
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