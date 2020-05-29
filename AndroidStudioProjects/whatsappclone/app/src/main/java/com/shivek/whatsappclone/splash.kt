package com.shivek.whatsappclone

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class splash : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 2000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this,login::class.java))
            finish()

        }, SPLASH_TIME_OUT)
    }
}