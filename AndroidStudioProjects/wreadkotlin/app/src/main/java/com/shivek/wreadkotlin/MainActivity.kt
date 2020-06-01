package com.shivek.wreadkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        write.setOnClickListener {
            val s = e1.text.toString()
            val f = ContextCompat.getDataDir(this)
            val g = File(f , "abc.txt")

         g.writeText(s)


        }

        read.setOnClickListener {
            val f = ContextCompat.getDataDir(this)
            val g = File(f , "abc.txt")

            t1.text = g.readText().toString()
        }
    }
}
