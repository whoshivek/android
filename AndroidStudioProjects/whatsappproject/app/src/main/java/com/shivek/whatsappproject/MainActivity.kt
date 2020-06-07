package com.shivek.whatsappproject

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import com.varunjohn1990.iosdialogs4android.IOSDialog


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.drawerlayout
import kotlinx.android.synthetic.main.activity_main.menutry
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.text.Typography.section

var defaultcc : String? = null
var defaultcp : String? = null
class MainActivity : AppCompatActivity() {



    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cccc = PreferenceManager.getDefaultSharedPreferences(this).getString("ccp" , "IN")
        if (cccc != null) {
            ccp.setDefaultCountryUsingNameCodeAndApply(cccc)
        }


   //

        ccp.setOnCountryChangeListener {
            number.setText(null)
        }

        number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkinput();
            }
        })

        sharelink.setOnClickListener {

            val intentt = Intent().apply {
                action = Intent.ACTION_SEND
                val i = ccp.selectedCountryCode + number.text
                val m = message.text.toString()
                if (m.isNullOrBlank()) {
                    putExtra(Intent.EXTRA_TEXT, "wa.me/${i}")
                } else {
                    putExtra(Intent.EXTRA_TEXT, "wa.me/${i}?text=${m}")
                }
                type = "text/plain"

            }

            val i = Intent.createChooser(intentt, null)
            if (ccp.selectedCountryCode == "91") {
                if (number.length() == 10) {
                    startActivity(i);
                    generate.visibility = View.VISIBLE
                    sharelink.visibility = View.GONE
                    copylink.visibility = View.GONE
                } else {
                    Toast.makeText(this, "Please enter 10 digits number", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (number.length() >= 6) {
                    startActivity(i);
                    generate.visibility = View.VISIBLE
                    sharelink.visibility = View.GONE
                    copylink.visibility = View.GONE
                } else {
                    Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show()
                }

            }

        }

        savedata.setOnClickListener {
            val intentt = Intent(Intent.ACTION_VIEW)
            intentt.setPackage("com.whatsapp")
            val i = ccp.selectedCountryCode + number.text
            val m = message.text.toString()
            intentt.data = Uri.parse("https://wa.me//${i}?text=${m}")

            if (packageManager.resolveActivity(intent, 0) != null) {
                if (ccp.selectedCountryCode == "91") {
                    if (number.length() == 10) {
                        if (isPackageInstalled("com.whatsapp", packageManager)) {
                            startActivity(intentt)
                        } else {
                            Toast.makeText(
                                this,
                                "WhatsApp not installed",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Please enter 10 digits number",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    if (number.length() >= 6) {
                        if (isPackageInstalled("com.whatsapp", packageManager)) {
                            startActivity(intentt)
                        } else {
                            Toast.makeText(
                                this,
                                "WhatsApp not installed",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }


        }

        copylink.setOnClickListener {


            if (ccp.selectedCountryCode == "91") {
                if (number.length() == 10) {
                    Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
                    generate.visibility = View.VISIBLE
                    sharelink.visibility = View.GONE
                    copylink.visibility = View.GONE
                    copylinkk()
                } else {
                    Toast.makeText(this, "Please enter 10 digits number", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (number.length() >= 6) {
                    Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
                    generate.visibility = View.VISIBLE
                    sharelink.visibility = View.GONE
                    copylink.visibility = View.GONE
                    copylinkk()
                } else {
                    Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show()
                }


            }

        }


        whatsappbusiness.setOnClickListener {
            val intentt = Intent(Intent.ACTION_VIEW)
            intentt.setPackage("com.whatsapp.w4b")
            val i = ccp.selectedCountryCode + number.text
            val m = message.text.toString()
            intentt.data = Uri.parse("https://wa.me//${i}?text=${m}")


            if (packageManager.resolveActivity(intent, 0) != null) {
                if (ccp.selectedCountryCode == "91") {
                    if (number.length() == 10) {
                        if (isPackageInstalled("com.whatsapp.w4b", packageManager)) {
                            startActivity(intentt)
                        } else {
                            Toast.makeText(
                                this,
                                "WhatsApp Business not installed",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(this, "Please enter 10 digits number", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    if (number.length() >= 6) {
                        if (isPackageInstalled("com.whatsapp.w4b", packageManager)) {
                            startActivity(intentt)
                        } else {
                            Toast.makeText(
                                this,
                                "WhatsApp Business not installed",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }


        generate.setOnClickListener {

            if (ccp.selectedCountryCode == "91") {
                if (number.length() == 10) {
                    sharelink.visibility = View.VISIBLE
                    copylink.visibility = View.VISIBLE
                    generate.visibility = View.GONE
                } else {
                    Toast.makeText(this, "Please enter 10 digits number", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (number.length() >= 6) {
                    sharelink.visibility = View.VISIBLE
                    copylink.visibility = View.VISIBLE
                    generate.visibility = View.GONE
                } else {
                    Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show()
                }


            }


        }

        switchsend.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.buttonTintList
            message.visibility = View.VISIBLE
           // logo.visibility = View.GONE

            if (!isChecked) {
                message.hideKeyboard()
                message.visibility = View.GONE
                message.text = null
                //logo.visibility = View.VISIBLE
            }
        }

        val navview : NavigationView = findViewById(R.id.nav_view)

        menutry.setOnClickListener {
                    if (!drawerlayout.isDrawerOpen(Gravity.START))
                    {
                        drawerlayout.openDrawer(Gravity.START)
                    }
        }



       switchsend.setOnTouchListener(object  :View.OnTouchListener{
           override fun onTouch(v: View?, event: MotionEvent?): Boolean {
               if (ccp.selectedCountryCode == "91") {
                   if (number.length() == 10) {
                     return false
                   } else {
                       Toast.makeText(this@MainActivity, "Please enter 10 digits number", Toast.LENGTH_SHORT).show()
                   }
               } else {
                   if (number.length() >= 6) {
                    return false
                   } else {
                       Toast.makeText(this@MainActivity, "Please enter phone number", Toast.LENGTH_SHORT).show()
                   }


               }
           return true
           }

       })


        defaultcc = ccp.defaultCountryNameCode
        defaultcp = ccp.defaultCountryCodeWithPlus


        navview.menu.getItem(1).setTitle("My Country - ${ccp.defaultCountryNameCode} (${ccp.defaultCountryCodeWithPlus})")

        navview.menu.getItem(0).setChecked(true)
        navview.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_home ->
                {
                    drawerlayout.closeDrawer(Gravity.START )
                    true
                }
                R.id.nav_gallery ->
                {
                    startActivity(Intent(this , MainActivity2::class.java))
                    finish()
                    false

                }
                else -> true
            }
        }

    }


    fun copylinkk(){

        val i = ccp.selectedCountryCode + number.text
        val m = message.text.toString()
        if (m.isNullOrBlank()) {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label","wa.me/${i}" )
            clipboard.setPrimaryClip(clip)

        } else {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label","wa.me/${i}?text=${m}" )
            clipboard.setPrimaryClip(clip)

        }


    }
    @RequiresApi(Build.VERSION_CODES.O)
        private fun checkinput() {
            if (ccp.selectedCountryCode == "91") {
                if (number.text?.length == 10) {
                  //  message.visibility = View.VISIBLE
                    number.limitLength(10)
                    number.hideKeyboard()
                    switchsend.isClickable = true;

                } else {
                    sharelink.visibility = View.GONE
                    copylink.visibility = View.GONE
                    generate.visibility = View.VISIBLE
                    message.visibility = View.GONE
                    switchsend.isClickable = false;
                    switchsend.isChecked = false;
                }
            } else {
                number.limitLength(13)
                if (number.text?.length!! >= 6) {
                  //  message.visibility = View.VISIBLE
                    switchsend.isClickable = true;

                } else {
                    sharelink.visibility = View.GONE
                    copylink.visibility = View.GONE
                    generate.visibility = View.VISIBLE
                    message.visibility = View.GONE
                    switchsend.isClickable = false
                    switchsend.isChecked = false
                }
            }
        }


        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }

    private fun TextInputEditText.limitLength(maxLength: Int) {
        filters = arrayOf(InputFilter.LengthFilter(maxLength))
    }

    fun isPackageInstalled(
        packageName: String?,
        packageManager: PackageManager
    ): Boolean {
        return try {
            packageManager.getApplicationInfo(packageName, 0).enabled
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    override fun onBackPressed() {
      /*  MaterialAlertDialogBuilder(this).apply {
            setTitle("Whatsdirect")
            setCancelable(false)
            setMessage("Are you sure you want to exit the app?")
            setPositiveButton("Yes" ,{ _ ,_ ->

                finishAffinity()
            })
            setNegativeButton("No" , { dialog, which ->
                dialog.dismiss()
            })
         create()
            show()
        }*/

IOSDialog.Builder(this).apply {
    title("WhatsDirect")
    message("Are you sure you want to exit the app?")
    positiveButtonText("YES")
    negativeButtonText("NO")

    positiveClickListener {
             it.dismiss()
             finishAffinity()

    }
    negativeClickListener { iosDialog ->

        iosDialog.dismiss()
    }
    cancelable(false)


}
    .build()
    .show()
    }


}


