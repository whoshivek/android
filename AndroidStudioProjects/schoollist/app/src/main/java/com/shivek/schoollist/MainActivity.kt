package com.shivek.schoollist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response

class MainActivity : AppCompatActivity() {
    val list = arrayListOf<dataclass>()
    val adapter = com.shivek.schoollist.adapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchdata()
    }

    private fun fetchdata() {
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.response.clone().execute()}

            if (response.isSuccessful)
            {
                val gson = Gson().fromJson(response.body?.string() , Response::class.java)
                launch(Dispatchers.Main){

                    binddata(gson.dataclass?)
                }
            }
            if (response.isRedirect)
            {
                launch(Dispatchers.Main) { Toast.makeText(this@MainActivity,"no net", Toast.LENGTH_SHORT).show()  }
            }
        }
    }

    private fun binddata(dataclass: Any?) {
        TODO("Not yet implemented")
    }

}