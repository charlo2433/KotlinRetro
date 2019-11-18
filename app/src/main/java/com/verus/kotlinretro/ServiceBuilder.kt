package com.verus.kotlinretro

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder{
    private const val URL = "https://jsonplaceholder.typicode.com/"

    private val okHttp = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private  val retrofit = builder.build()

    fun <T> builderService(ServiceType: Class<T>) :T{
        return retrofit.create(ServiceType)
    }
}