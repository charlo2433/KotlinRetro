package com.verus.kotlinretro

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
        @GET("todo")
        fun getData() : Observable<List<Post>>
}
