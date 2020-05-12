package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trans.trans
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b = Bundle()
        b.putString("KEY","name")
        val f = BlankFragment()
        f.arguments=b

    val c = viewpager(supportFragmentManager)
        c.apply {
            add(BlankFragment())
            add(BlankFragment())
            add(BlankFragment())
        }
        container.adapter = c
        container.setPageTransformer(true,trans())






    }
}
