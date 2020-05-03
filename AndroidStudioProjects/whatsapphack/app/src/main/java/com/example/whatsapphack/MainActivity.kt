package com.example.whatsapphack

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          var c : String = "0"
        if(intent.action== Intent.ACTION_PROCESS_TEXT)
        {
           c= intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()

        }
        if(c.isDigitsOnly())
        {
              startwhatsapp(c)
        }
        else
        {
         Toast.makeText(this,"not a number", Toast.LENGTH_SHORT).show()
        }
        
    }

    private fun startwhatsapp(c: String) {
              val d = Intent(Intent.ACTION_VIEW)
               d.setPackage("com.whatsapp")

        val data : String =
        if(c[0]=='+')
        {
            c.substring(1)

        }
        else if(c.length==10)
        {
            "91"+c
        }
        else {
                      c
        }
        d.data = Uri.parse("https://wa.me//$data")
        if (packageManager.resolveActivity(intent,0)!=null)
        {
            startActivity(d)
        }
        finish()
    }
}
