package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fruits.*
import kotlinx.android.synthetic.main.fruits.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvf.adapter = ArrayAdapter<String>(
            this,
            R.layout.fruits,
            R.id.textv ,
                arrayOf("apple" , "mango" , "a" , "b" , "c", "d" , "e" , "f" , "g")

        )

        lvf.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this , "shivek ate ${position+1} ${view.textv.text}" ,Toast.LENGTH_LONG).show()

        }
    }
}
