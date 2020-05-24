package com.shivek.todo

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about.*

class about : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

   dm.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        dm.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.instagram.com/who_shivek/")
            startActivity(i)
        }

        report.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        report.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfnDbn5XclXt0PnUMSH_uYO6-uCnC_tQYiVnhI0-QUu-W88DQ/viewform")
            startActivity(i)
        }
    }
}
