package com.example.kotlinnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ok = OkHttpClient()

        val request = Request.Builder()


        .url("https://api.github.com/users/championswimmer")
            .build()
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        GlobalScope.launch(Dispatchers.Main){
            val response = withContext(Dispatchers.IO){ ok.newCall(request).execute().body?.string()}

                     val user = gson.fromJson<User>(response , User::class.java)

            t1.text =user.login
            t2.text =user.avatarUrl
            Picasso.get().load(user.avatarUrl).into(imageView);

            }
        }



    }

