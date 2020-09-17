package com.manish.pdfreaderrr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this, MainActivity2::class.java))
            finish()

        }, SPLASH_TIME_OUT)
    }
}