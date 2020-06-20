package com.shivek.schoollist


import okhttp3.OkHttpClient
import okhttp3.Request

object Client {

    private  val client = OkHttpClient()
    private val request = Request.Builder().url("https://s5.aconvert.com/convert/p3r68-cdx67/qup70-6j7rr.json").build()
    val response = client.newCall(request)
}