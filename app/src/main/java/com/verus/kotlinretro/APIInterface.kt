package com.verus.kotlinretro

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

        @GET("todo")
        fun getData(): Call<List<Post>>

}
