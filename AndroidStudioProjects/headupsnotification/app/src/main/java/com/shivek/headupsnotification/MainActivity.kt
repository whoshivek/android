package com.shivek.headupsnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
              val channel = NotificationChannel(
                      "first",
                      "default",
                      NotificationManager.IMPORTANCE_HIGH
              )
            channel.apply {
                enableLights(true)
                enableVibration(true)
            }

            nm.createNotificationChannel(channel)

            button.setOnClickListener {

                val builder =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                        {
                            Notification.Builder(this, "first")
                        }
                else{
                          Notification.Builder(this)
                                  .setPriority(Notification.PRIORITY_HIGH)
                                  .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS)

                        }

                val simplenotification = builder
                        .setContentTitle("SKDJK")
                        .setContentText("fjdfjshfishciushfik000")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .build()

                nm.notify(1,simplenotification)

            }


        }
    }
}
