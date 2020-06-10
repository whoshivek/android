package com.shivek.factsapi
import okhttp3.OkHttpClient
import okhttp3.Request


object Client {

    var client = OkHttpClient()

    var request: Request = Request.Builder()
        .url("https://community-open-weather-map.p.rapidapi.com/weather?callback=test&id=2172797&units=%2522metric%2522%20or%20%2522imperial%2522&mode=xml%252C%20html&q=delhi")
        .get()
        .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
        .addHeader("x-rapidapi-key", "8cf1b2edb3msh56dd978d0402603p188c7ajsnc31153434eb4")
        .build()

    var response = client.newCall(request)
}
