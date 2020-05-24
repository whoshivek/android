package com.shivek.todowebinar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var array = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adpter = ArrayAdapter<String>(
            this,
            android.R.layout.activity_list_item,
            android.R.id.text1,
            array
        )

        l1.adapter = adpter


        b1.setOnClickListener {
            val d = e1.text.toString()
            array.add(d)
           adpter.notifyDataSetChanged()
            e1.text = null

        }



    }
}
