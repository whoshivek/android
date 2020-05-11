package com.example.fragddd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fruit.setOnClickListener(
            {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.hh , vegetable())
                    .commit()
            }
        )

        veg.setOnClickListener(
            {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.hh , com.example.fragddd.fruit())
                    .commit()
            }
        )
    }
}
