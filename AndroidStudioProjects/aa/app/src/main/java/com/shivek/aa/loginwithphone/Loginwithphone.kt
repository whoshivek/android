package com.shivek.aa.loginwithphone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.shivek.aa.R
import com.shivek.aa.loginwithemail.login
import kotlinx.android.synthetic.main.activity_loginwithphone.*

class loginwithphone : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginwithphone)


        phone.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                  checkinput()
            }
        })

        email.setOnClickListener {
            startActivity(Intent(this, login::class.java))
        }

        mobilelogin.setOnClickListener {

            val i = Intent(this , verificationcode::class.java )
            i.putExtra("pn", phone.text.toString())
            startActivity(i)
        }


    }

    private fun checkinput() {
        if (phone.text.toString().length >= 10)
        {
            mobilelogin.isEnabled = true
            mobilelogin.setTextColor(getColor(R.color.LightBlue))
            mobilelogin.setBackgroundColor(Color.BLUE)

        }

        else{
            mobilelogin.isEnabled= false
            mobilelogin.setTextColor(Color.BLUE)
            mobilelogin.setBackgroundColor(getColor(R.color.LightBlue))
        }
    }
}
