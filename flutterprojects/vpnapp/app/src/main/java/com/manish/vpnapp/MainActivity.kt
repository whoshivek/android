package com.manish.vpnapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import kotlinx.android.synthetic.main.activity_main.*
import me.saket.bettermovementmethod.BetterLinkMovementMethod

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        exit.setOnClickListener {
            finishAffinity()
        }

        termofservice.movementMethod = BetterLinkMovementMethod.getInstance()
    }
}