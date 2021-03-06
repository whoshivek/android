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
import com.shivek.aa.R
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {

    private val emailpattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$"



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signin.setOnClickListener {
            startActivity(Intent(this, login::class.java))
            overridePendingTransition(
                R.anim.fadein,
                R.anim.fadeout
            )
            finish()

        }

        name.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               checkinput()
            }
        }
        )

        email.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
              checkinput()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        password.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkinput()
            }
        }
        )
        confirmpassword.addTextChangedListener(object : TextWatcher

        {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkinput()
            }
        }
        )

        ss.setOnClickListener {
            checkemailandpassword()
        }

    }

    private fun checkemailandpassword() {
        if (email.text.toString().matches(emailpattern.toRegex()))
            {
                if (password.text.toString().equals(confirmpassword.text.toString()))
                {
                    progressbar.visibility = View.VISIBLE
                    ss.isEnabled = false
                    val username = hashMapOf<String, Any>()
                    username.put("name",name.text.toString())


                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                        .addOnCompleteListener{
                            if (it.isSuccessful) {
                                FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                                    ?.addOnCompleteListener {
                                        if (it.isSuccessful) {
                                               Toast.makeText(this,"REGISTERED SUCCESSFULLY \n VERIFY YOUR EMAIL",Toast.LENGTH_LONG).show()
                                            val i = Intent(this,
                                                login::class.java)
                                         i.putExtra("result" ,email.text.toString() )
                                            i.putExtra("name",name.text.toString())

                                            startActivity(i)
                                            FirebaseAuth.getInstance().signOut()
                                        }


                                    }
                            }else {progressbar.visibility = View.GONE
                                            ss.isEnabled = true
                                            val error = it.exception?.message
                                            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                                        }

                        }

                }
                else
                {
                      password.setError("Password does not matched")
                }

            }
        else{
            email.setError("NOT a Valid Email")
        }
    }

    private fun checkinput() {
       if (name.text.toString().isNotEmpty())
       {
           if (email.text.toString().isNotEmpty())
           {
               if (password.text.toString().isNotEmpty() && password.text.toString().length >=6)
               {
                   if (confirmpassword.text.toString().isNotEmpty())
                   {
                       ss.isEnabled= true
                       ss.setTextColor(getColor(R.color.LightBlue))
                       ss.setBackgroundColor(Color.BLUE)
                   }
                   else{        ss.isEnabled= false
                       ss.setTextColor(Color.BLUE)
                       ss.setBackgroundColor(getColor(R.color.LightBlue))


                   }

               }
               else
               {ss.isEnabled= false
                   ss.setTextColor(Color.BLUE)
                   ss.setBackgroundColor(getColor(R.color.LightBlue))

               }
           }else
           {
               ss.isEnabled= false
               ss.setTextColor(Color.BLUE)
               ss.setBackgroundColor(getColor(R.color.LightBlue))
           }
       }
        else{
           ss.isEnabled= false
           ss.setTextColor(Color.BLUE)
           ss.setBackgroundColor(getColor(R.color.LightBlue))
       }
    }
}
