package com.example.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fruit = fruits.fruitarray(100)
        val Fruitadapter = fruitadapter(fruit)

        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = Fruitadapter

    }
}
