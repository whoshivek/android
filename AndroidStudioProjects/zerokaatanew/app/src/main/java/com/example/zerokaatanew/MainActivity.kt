package com.example.zerokaatanew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        play.setOnClickListener(
            {
                val c = Intent(this ,Main2Activity::class.java)
                startActivity(c)

            }
        )

        exit.setOnClickListener(
            {
                Toast.makeText(applicationContext , "thankyou for playing" , Toast.LENGTH_SHORT).show()
                finishAffinity()




            }
        )
    }
}
