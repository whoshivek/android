package com.shivek.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val i = Intent(baseContext , Main2Activity::class.java)

            val pi = PendingIntent.getActivities(baseContext,123, arrayOf(i),PendingIntent.FLAG_UPDATE_CURRENT)

            val j = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            j.set(AlarmManager.ELAPSED_REALTIME_WAKEUP , SystemClock.elapsedRealtime() + 30000 , pi)
        }







    }
}
