package com.shivek.aa.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.shivek.aa.maincontent.MainActivity
import com.shivek.aa.R
import com.shivek.aa.loginwithemail.login
import com.shivek.aa.loginwithphone.loginwithphone

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 2000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
val user = FirebaseAuth.getInstance().currentUser

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            if (user == null ) {

                startActivity(Intent(this, loginwithphone::class.java))
                finish()
            }

            if (user != null) {
                if (user.isEmailVerified)
                    {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                else
                {
                    startActivity(Intent(this, loginwithphone::class.java))
                    finish()
                }
            }

        }, SPLASH_TIME_OUT)
    }
}