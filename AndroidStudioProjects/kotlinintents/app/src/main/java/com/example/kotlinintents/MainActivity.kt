package com.example.kotlinintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

const val KEY_1= "result"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(
            {
                val c= e1.text.toString().toInt() + e2.text.toString().toInt()
                val v = Intent(this , Main2Activity::class.java)
                v.putExtra(KEY_1,c)
                startActivity(v)
            }
        )


    }
}
