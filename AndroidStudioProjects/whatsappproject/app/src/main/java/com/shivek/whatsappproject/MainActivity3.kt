package com.shivek.whatsappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener

import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        backexit.setOnClickListener {
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }


        val header : MutableList<String> = ArrayList()
        val body : MutableList<MutableList<String>> = ArrayList()

        header.add("hi")
        val hi :MutableList<String> = ArrayList()
        hi.add("hell")
        body.add(hi)

        expandable.setAdapter(exlistadapter(this , header ,body))
    }

    override fun onBackPressed() {
        exitbutton()
    }

    private fun exitbutton() {
        iOSDialogBuilder(this).apply {
            setTitle("WhatsDirect")
            setSubtitle("Are you sure to exit the app?")
            setBoldPositiveLabel(false)
            setCancelable(false)
            setPositiveListener("YES", iOSDialogClickListener {
                finishAffinity()
                it.dismiss()
            })
            setNegativeListener("NO", iOSDialogClickListener {
                it.dismiss()
            })

        }
            .build()
            .show()
    }
}