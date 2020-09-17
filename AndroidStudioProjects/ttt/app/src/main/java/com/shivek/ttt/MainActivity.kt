package com.shivek.ttt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {

        private val SPLASH_TIME_OUT: Long = 2000 // 1 sec
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)


            Handler().postDelayed({

                startActivity(Intent(this, MainActivity2::class.java))
                finish()


            }, SPLASH_TIME_OUT)
        }
    }

