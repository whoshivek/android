package com.shivek.mvvmgit.data.api

import com.shivek.mvvmgit.data.models.Allgituser
import okhttp3.Response
import retrofit2.http.GET

interface githubservice
{
    @GET("users")
    suspend fun getusers() : retrofit2.Response<List<Allgituser>>




}