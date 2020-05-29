package com.shivek.whatsappclone

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Message
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otp.*
import java.util.concurrent.TimeUnit

const val PHONE_NO = "phone no."

class otpActivity : AppCompatActivity() {

    lateinit var mCallbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var mverification : String? = null
    var mresend  :PhoneAuthProvider.ForceResendingToken? = null
    private var mcounter : CountDownTimer?= null
private lateinit var progress : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        verify()

        val phoneno = intent.getStringExtra(PHONE_NO)
        verifyno.text = "VERIFY $phoneno"

        span()

        veri.setOnClickListener {
            authenticate()
        }


    }

    private fun authenticate() {
        val c = otppp.text
        
    }

    fun  callbacks()
    {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                val smscode = p0.smsCode
                if (!smscode.isNullOrBlank())
                {
                    otppp.setText(smscode)
                }

                if (::progress.isInitialized)
                {
                    progress.dismiss()
                }

                signin(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                val error = p0.message
                 notifyuserandretry(error)

                if (::progress.isInitialized)
                {
                    progress.dismiss()
                }
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                mverification = p0
                mresend = p1
                if (::progress.isInitialized)
                {
                    progress.dismiss()
                }

            }
        }
    }

    private fun notifyuserandretry(error: String?) {
          MaterialAlertDialogBuilder(this).apply {
              setMessage(error)
              setPositiveButton("RETRY"){ _,_->
                  startActivity(Intent(this@otpActivity,login::class.java))
              }
              setNegativeButton("CANCEL"){ dialog, which ->
              dialog.dismiss()
              }
              setCancelable(false)
              create()
              show()
          }
    }

    private fun signin(p0 : PhoneAuthCredential) {
        progress = createprogress("wait verifying" , false)
        progress.show()
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithCredential(p0)
            .addOnCompleteListener{
                if (it.isSuccessful)
                {
                         startActivity(Intent(this,welcome::class.java))
                }
                else{
                    val i = it.exception?.message
                    notifyuserandretry(i)

                }
            }

    }

    private fun verify() {
        countertimer(60000)
        callbacks()
        val phoneno = intent.getStringExtra(PHONE_NO)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneno,        // Phone number to verify
            60,                 // Timeout duration
            TimeUnit.SECONDS,   // Unit of timeout
            this,               // Activity (for callback binding)
            mCallbacks);        // OnVerificationStateChangedCallbacks

        progress = createprogress("sending verification code" , false)
        progress.show()
    }

    private fun countertimer(timeinmillfuture: Long) {
        resend.isEnabled = false
      mcounter=  object : CountDownTimer(timeinmillfuture,1000){
            override fun onFinish() {
                counter.visibility = View.INVISIBLE
               resend.isEnabled = true
            }

            override fun onTick(millisUntilFinished: Long) {
               counter.visibility = View.VISIBLE
              counter.text = "Seconds Remaining : ${millisUntilFinished/1000}"
            }
        }.start()

    }

    private fun span() {
        val phoneno = intent.getStringExtra(PHONE_NO)
        val span = SpannableString(getString(R.string.auto,phoneno))
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                   startActivity(Intent(this@otpActivity,login::class.java))
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = ds.linkColor
            }
        }
       val StyleSpan = StyleSpan(android.graphics.Typeface.BOLD)
        span.setSpan(StyleSpan,span.length-27 , span.length-15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        span.setSpan(clickableSpan,span.length -13, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        wating.text = span
        wating.movementMethod = LinkMovementMethod.getInstance()

    }

    override fun onBackPressed() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mcounter != null)
        {
            mcounter!!.cancel()
        }
    }


}

fun Context.createprogress(message : String, iscancelable : Boolean) : ProgressDialog{
    return ProgressDialog(this).apply {
             setMessage(message)
        setCancelable(iscancelable)
        setCanceledOnTouchOutside(iscancelable)


    }
}
