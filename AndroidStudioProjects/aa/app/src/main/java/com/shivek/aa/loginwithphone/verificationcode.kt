package com.shivek.aa.loginwithphone

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.arch.core.executor.TaskExecutor
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.shivek.aa.R
import com.shivek.aa.maincontent.MainActivity
import kotlinx.android.synthetic.main.activity_verificationcode.*
import java.util.concurrent.TimeUnit

class verificationcode : AppCompatActivity() {
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mAuth: FirebaseAuth
    var verificationId = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificationcode)

        mAuth = FirebaseAuth.getInstance()
        val n = intent.getStringExtra("pn")

    sendverificationcode(n)


vb.setOnClickListener {
    pbb.visibility = View.VISIBLE
    authenticate()
}
    }

    private fun authenticate() {
        val verify = phone.text.toString()
        val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,verify)
        signinnn(credential)
    }

    fun callbacks()
    {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
            {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    auto.visibility = View.GONE
                    pbb.visibility = View.GONE
                    phone.isClickable= true
                    vb.isEnabled = true
                    signinnn(p0)

                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    pbb.visibility = View.GONE
                    auto.visibility = View.GONE

                  Toast.makeText(this@verificationcode , p0.message,Toast.LENGTH_SHORT).show()
                }

                override fun onCodeSent(verify: String, p1: PhoneAuthProvider.ForceResendingToken) {

                    verificationId = verify
                    super.onCodeSent(verify, p1)
                }
            }

    }

    private fun signinnn(p0: PhoneAuthCredential) {

        mAuth.signInWithCredential(p0)
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                else{
                    pbb.visibility = View.GONE
                    val error = it.exception?.message
                    Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun sendverificationcode(n: String?) {
        auto.visibility = View.VISIBLE
        pbb.visibility = View.VISIBLE
        phone.isClickable = false
        vb.isEnabled = false
        callbacks()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91${n}", // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            mCallbacks
        ) // OnVerificationStateChangedCallbacks






    }
}



