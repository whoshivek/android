package com.example.covid19tracker

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object Client {

  private  val client = OkHttpClient()
    private val request = Request.Builder().url("https://api.covid19india.org/data.json").build()
    val response = client.newCall(request)
}