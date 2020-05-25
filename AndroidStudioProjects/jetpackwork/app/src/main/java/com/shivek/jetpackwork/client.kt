package com.shivek.jetpackwork

import okhttp3.OkHttpClient
import okhttp3.Request

object client
{
    val i = OkHttpClient()
      val request =     Request.Builder().url("http://api.weatherapi.com/v1/current.json?key=4812e98dbddb4b4597b51657202505&q=india").build()
       val response =i.newCall(request)
}