package com.shivek.dynamicbroadcaster

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val o = breciever()
        val i = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
           addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_HEADSET_PLUG)
        }

        registerReceiver(o , i)



    }

    inner class breciever: BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?) {
           Toast.makeText(this@MainActivity,"power on" , Toast.LENGTH_LONG).show()
        }

    }
}
