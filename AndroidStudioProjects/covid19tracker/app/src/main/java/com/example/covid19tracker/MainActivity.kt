package com.example.covid19tracker

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(isConnectedToNetwork()) {

            fetchdata()
        }
        else
        {

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
                    bindcombineddata(gson.statewise?.get(0))
                    bindstatedata(gson.statewise?.subList(0, gson.statewise?.size))
                }
            }
          if (response.isRedirect)
          {
              launch(Dispatchers.Main) {Toast.makeText(this@MainActivity,"no net",Toast.LENGTH_SHORT).show()  }
          }
        }
    }

    private fun bindstatedata(subList: List<StatewiseItem?>?) {

        val adapterapi = Gitadapter(subList as List<StatewiseItem>)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapterapi

    }


    private fun bindcombineddata(get: StatewiseItem?) {

val date = get?.lastupdatedtime
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")

        lastupdate.text ="Last updated\n ${timesago(simpleDateFormat.parse(date))}"
      confirm.text = get?.confirmed.toString()
        recovered.text = get?.recovered.toString()
       death.text = get?.deaths.toString()
        active.text =get?.active.toString()
        deltaconfirmed.text= "[↑${get?.deltaconfirmed.toString()}]"
        deltarecover.text= "[↑${get?.deltarecovered.toString()}]"
        deltadeath.text= "[↑${get?.deltadeaths.toString()}]"

    }

    private fun timesago(past : Date): String {

        val now = Date()
        val seconds :Long = TimeUnit.MILLISECONDS.toSeconds(now.time-past.time)
        val minutes : Long = TimeUnit.MILLISECONDS.toMinutes(now.time-past.time)
        val hours : Long = TimeUnit.MILLISECONDS.toHours(now.time-past.time)

        return when
        {
            seconds<60 -> {
                "few "
            }
            minutes<60 -> {
                "$minutes minutes ago"
            }
            hours<24 ->
            {
                "$hours hours ago"
            }
            else -> { SimpleDateFormat("dd/MM/yy , hh:mm a").format(past).toString()}
        }

    }


}
