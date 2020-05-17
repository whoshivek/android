package com.example.kotlinretrofit

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface gitinterface {

@GET("users")
  suspend  fun get() : retrofit2.Response<List<Gitusers>>

    @GET("users/{id}")
    suspend  fun get(@Path("id") id : Int) : retrofit2.Response<Gitusers>

    @GET("search/users")
    suspend  fun get(@Query("q") query: String) : List<Gitusers>


}