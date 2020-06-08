package com.shivek.whatsappproject

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import android.view.Menu
import android.widget.Toast
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.drawerlayout
import me.ibrahimsn.lib.OnItemSelectedListener

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var i : String? = null
        var j : String? = null


        ccc.setOnCountryChangeListener {
            i = ccc.selectedCountryName
            j = ccc.selectedCountryNameCode.toString()
        }

if (i.isNullOrBlank())
{
    i = "India"
}



        set.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("ccp", j).apply()
            startActivity(Intent(this , MainActivity::class.java))
            finish()
            Toast.makeText(this , "Now, ${i}(${ccc.selectedCountryCodeWithPlus}) is your default country" , Toast.LENGTH_LONG).show()
        }
        PreferenceManager.getDefaultSharedPreferences(this).getString("ccp" , "IN")?.let {
            ccc.setDefaultCountryUsingNameCodeAndApply(
                it
            )
        }

back.setOnClickListener {
    startActivity(Intent(this , MainActivity::class.java))
    finish()
}



    }

    private fun exitdialog() {
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


    override fun onBackPressed() {
        exitdialog()
    }


}

