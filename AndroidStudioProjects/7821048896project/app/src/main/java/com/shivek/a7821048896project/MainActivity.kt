package com.shivek.a7821048896project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insertdetail.setOnClickListener {
             startActivity(Intent(this , Insertdetail::class.java))
        }
        showdetails.setOnClickListener {
          startActivity(Intent(this , showdetails1::class.java))
        }
    }
}