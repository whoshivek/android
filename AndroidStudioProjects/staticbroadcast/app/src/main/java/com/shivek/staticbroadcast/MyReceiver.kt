package com.shivek.staticbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context,"headphones plugged in" , Toast.LENGTH_SHORT).show()
        context.startActivity(Intent(context , MainActivity::class.java))
    }
}
