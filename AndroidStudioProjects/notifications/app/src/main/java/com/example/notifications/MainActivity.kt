package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            nm.createNotificationChannel( NotificationChannel("first","default" , NotificationManager.IMPORTANCE_DEFAULT))
        }


        button.setOnClickListener{
            val simplenotification = NotificationCompat.Builder(this,"first")
                    .setContentTitle("shivek")
                    .setContentText("hgyhgygujgujgujgu")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()

            nm.notify(1 , simplenotification)

        }

        button2.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.co.in")
             val pi = PendingIntent.getActivities(this,123, arrayOf(i), PendingIntent.FLAG_UPDATE_CURRENT)
            val simplenotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("shivek")
                .setContentText("hgyhgygujgujgujgu")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            nm.notify(2,simplenotification)

        }

        button3.setOnClickListener {

            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.co.in")
            val pi = PendingIntent.getActivities(this,123, arrayOf(i), PendingIntent.FLAG_UPDATE_CURRENT)
            val simplenotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("shivek")
                .setContentText("hgyhgygujgujgujgu")
                .addAction(R.drawable.ic_launcher_background ,"click me" , pi)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            nm.notify(3,simplenotification)
        }



    }
}
