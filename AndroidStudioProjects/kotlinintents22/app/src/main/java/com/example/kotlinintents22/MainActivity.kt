package com.example.kotlinintents22

import android.app.Notification
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dial.setOnClickListener(
            {
                val c = enter.text.toString()
                val i = Intent()
                i.action = Intent.ACTION_DIAL
                i.data = Uri.parse("tel:$c")
                startActivity(i)
            }
        )

        search.setOnClickListener(
            {
                val c = enter.text.toString()
                val i= Intent()
                i.action = Intent.ACTION_VIEW
                i.data = Uri.parse("http://$c")
                startActivity(i)
            }
        )

        mail.setOnClickListener(
            {
                val c = enter.text.toString()
                val i = Intent()
                i.action= Intent.ACTION_SENDTO
                i.data = Uri.parse("mailto:$c")
                startActivity(i)
            }
        )
        second.setOnClickListener(
            {
                val i = Intent(this , Main2Activity::class.java)
                startActivity(i)
            }
        )
    }
}
