package com.shivek.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_launchactivity.*

class launchactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launchactivity)


        floa.setOnClickListener{
            startActivity(Intent(this,task::class.java))
        }
    }
}
