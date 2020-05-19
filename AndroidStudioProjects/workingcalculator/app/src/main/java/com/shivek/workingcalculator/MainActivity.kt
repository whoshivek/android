package com.shivek.workingcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var c = 0
    var d = 0


    var add = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1.text = null

        button6.setOnClickListener {
            e1.text = "${e1.text}1"

        }

        button7.setOnClickListener {
            e1.text = "${e1.text}2"

        }
        button8.setOnClickListener {
            e1.text = "${e1.text}3"

        }
        button9.setOnClickListener {
            e1.text = "${e1.text}4"

        }

        button10.setOnClickListener {
            e1.text = "${e1.text}5"

        }
        button11.setOnClickListener {
            e1.text = "${e1.text}6"

        }
        button12.setOnClickListener {
            e1.text = "${e1.text}7"

        }

        button13.setOnClickListener {
            e1.text = "${e1.text}8"

        }
        button14.setOnClickListener {
            e1.text = "${e1.text}9"

        }

        button15.setOnClickListener {
            e1.text = "${e1.text}0"
        }

        button5.setOnClickListener {
            e1.text = null
        }


button.setOnClickListener {

    if (e1.text== null)
    {
        Toast.makeText(applicationContext,"oops something wrong",Toast.LENGTH_SHORT).show()
    }
             c=Integer.valueOf(e1.text.toString())
               e1.text = null
                add = true



}
        button2.setOnClickListener {

            if (e1.text == null)
            {
                Toast.makeText(this,"enter no. first",Toast.LENGTH_SHORT).show()
            }
            if (add)
            {
                d= Integer.valueOf(e1.text.toString())
                val f = c + d
                e1.text = f.toString()
            }
        }




    }
}
