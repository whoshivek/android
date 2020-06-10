package com.shivek.factsapi

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var client = OkHttpClient()
          val q = ee.text.toString()
        var request: Request = Request.Builder()
            .url("https://community-open-weather-map.p.rapidapi.com/weather?callback=test&id=2172797&units=%2522metric%2522%20or%20%2522imperial%2522&mode=xml%252C%20html&q=delhi")
            .get()
            .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "8cf1b2edb3msh56dd978d0402603p188c7ajsnc31153434eb4")
            .build()

        var response = client.newCall(request)
        button.setOnClickListener {
            if (isConnectedToNetwork()) {

                fetchdata()
            } else {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("no internet")
                builder.setMessage("open your data/wifi")
                //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
                builder.setCancelable(false)
                builder.setPositiveButton("EXIT") { dialog, which ->
                    finishAffinity()
                }
                builder.show()
            }
        }


    }

    fun Context.isConnectedToNetwork(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }




    private fun fetchdata() {
        GlobalScope.launch {

            val response =withContext(Dispatchers.IO) { Client.response.clone().execute()}

            if (response.isSuccessful)
            {
                val gson = Gson().fromJson(response.body?.string() , Response::class.java)
                launch(Dispatchers.Main){
                    abc.text = gson.xCacheKey.toString()
                }
            }
            if (response.isRedirect)
            {
                launch(Dispatchers.Main) {Toast.makeText(this@MainActivity,"no net",Toast.LENGTH_SHORT).show()  }
            }
        }
    }


}