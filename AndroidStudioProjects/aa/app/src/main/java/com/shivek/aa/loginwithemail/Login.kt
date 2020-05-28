package com.shivek.aa.loginwithemail

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.shivek.aa.maincontent.MainActivity
import com.shivek.aa.R
import kotlinx.android.synthetic.main.login.*

class login : AppCompatActivity() {
    private val emailpattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        signup.setOnClickListener {
            startActivity(Intent(this, com.shivek.aa.loginwithemail.signup::class.java))
            overridePendingTransition(
                R.anim.fadein,
                R.anim.fadeout
            )
            finish()
        }

        val c = intent.getStringExtra("result")
        semail.setText(c)
        skip.setOnClickListener {
            FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else
                    {
                        val error = it.exception?.message
                        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()

                    }
                }
        }

        semail.addTextChangedListener(object  : TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                   checkinput()
            }
        })
        spassword.addTextChangedListener(object  : TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkinput()
            }
        })

        sin.setOnClickListener {
            checkemail()
        }

    }

    private fun checkemail() {
        if (semail.text.toString().matches(emailpattern.toRegex()))
        {
            if (spassword.text.toString().length >=6)
            {
                pb.visibility = View.VISIBLE
                sin.isEnabled = false
                FirebaseAuth.getInstance().signInWithEmailAndPassword(semail.text.toString(),spassword.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful)
                        {
                           if (FirebaseAuth.getInstance().currentUser?.isEmailVerified!!) {
                               startActivity(Intent(this, MainActivity::class.java))
                               finish()
                           }
                            else
                           {
                               sin.isEnabled = true
                             pb.visibility = View.GONE
                               Toast.makeText(this,"VERIFY YOUR EMAIL",Toast.LENGTH_SHORT).show()

                           }

                        }
                        else
                        {
                            pb.visibility = View.GONE
                            sin.isEnabled = true
                            val error = it.exception?.message
                            Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
                        }
                    }

            }
            else
            {
                Toast.makeText(this,"Incorrect email or password" ,Toast.LENGTH_SHORT).show()
            }

        }
        else
        {
            Toast.makeText(this,"Incorrect email or password" ,Toast.LENGTH_SHORT).show()

        }
    }

    private fun checkinput() {
        if (semail.text.toString().isNotEmpty())
        {
            if (spassword.text.toString().isNotEmpty())
            {
                sin.isEnabled = true
                sin.setTextColor(getColor(R.color.LightBlue))
                sin.setBackgroundColor(Color.BLUE)
            }
            else
            {
                sin.isEnabled= false
                sin.setTextColor(Color.BLUE)
                sin.setBackgroundColor(getColor(R.color.LightBlue))
            }

        }
        else
        {
            sin.isEnabled= false
            sin.setTextColor(Color.BLUE)
            sin.setBackgroundColor(getColor(R.color.LightBlue))
        }

    }
}
