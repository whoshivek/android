package com.example.fragsdynamic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fb.setOnClickListener(
            {
                supportFragmentManager
              .beginTransaction()
                .replace(R.id.lvv , fruits())
                    .commit()

            }
        )

        fv.setOnClickListener(
            {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.lvv , vegetable())
                    .commit()

            }
        )
    }
}
