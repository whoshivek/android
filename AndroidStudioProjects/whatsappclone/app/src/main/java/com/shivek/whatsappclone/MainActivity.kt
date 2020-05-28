package com.shivek.whatsappclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enter.addTextChangedListener(object :TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkinput()
            }
        })



    }

    private fun checkinput() {
        if (enter.text.toString().isNotEmpty())
        {
            if (!enter.text.toString().contains("+"))
            {if (enter.text.toString().length==10)
            {
                next.isEnabled = true
            }
            else
            {
                next.isEnabled = false
            }

            }
            else
            {
                next.isEnabled = false
            }

        }else
        {
            next.isEnabled = false
        }
    }
}