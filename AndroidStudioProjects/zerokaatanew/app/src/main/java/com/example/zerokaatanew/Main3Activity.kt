package com.example.zerokaatanew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.zerokaatanew.R
import kotlinx.android.synthetic.main.activity_main3.*


class Main3Activity : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        exit.setOnClickListener(
            {
                Toast.makeText(applicationContext, "thankyou for playing", Toast.LENGTH_SHORT)
                    .show()
                finishAffinity()
            }
        )

        back.setOnClickListener(
            {
                val c = Intent(this, Main2Activity::class.java)
                startActivity(c)
            }
        )
    }
}



