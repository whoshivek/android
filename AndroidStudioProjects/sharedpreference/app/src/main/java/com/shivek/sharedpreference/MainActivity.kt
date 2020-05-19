package com.shivek.sharedpreference

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
 val sp = getPreferences(Context.MODE_PRIVATE)
        val c =sp.getInt("COLOR" , Color.WHITE)
        ll.setBackgroundColor(c)

       fun savecolor(color : Int)
        {
            val editor = sp.edit()
            editor.putInt("COLOR" , color)
            editor.apply()
        }

        red.setOnClickListener {
            ll.setBackgroundColor(Color.RED)
            savecolor(Color.RED)
        }
        
        green.setOnClickListener { 
            ll.setBackgroundColor(Color.GREEN)
            savecolor(Color.GREEN)
        }
        
        blue.setOnClickListener { 
            ll.setBackgroundColor(Color.BLUE)
            savecolor(Color.BLACK)
        }
    }


}
