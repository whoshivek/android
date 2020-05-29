package com.shivek.whatsappclone

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
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
    private var istimeractive = false
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

        resend.setOnClickListener {
            if (mresend != null ) {
                val phoneno = intent.getStringExtra(PHONE_NO)
                countertimer(60000)
                resendVerificationCode(phoneno, mresend!!)
              
              progress = createprogress("Sending a verification code", false)
                progress.show()
            }
            else {
                Toast.makeText(this,"Sorry, You Can't request new code now, Please wait ...",Toast.LENGTH_LONG).show()
            }
        }



    }

    private fun resendVerificationCode(phoneno: String?, mresend: PhoneAuthProvider.ForceResendingToken) {
                 PhoneAuthProvider.getInstance().verifyPhoneNumber(
                     phoneno!!,
                     60,
                     TimeUnit.SECONDS,
                     this,
                     mCallbacks,
                     mresend
                 )
    }

    private fun authenticate() {

        val c = otppp.text.toString()
        if (c.isNotEmpty() && !mverification.isNullOrBlank()) {

            val credential: PhoneAuthCredential =
                PhoneAuthProvider.getCredential(mverification!!, c)
            signin(credential)
        }
        else{
            notifyuserandretry("OOPS SOMETHING WENT WRONG")
        }
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
                 recreate()

              }
              setNegativeButton("CANCEL"){ _, _ ->
                  startActivity(Intent(this@otpActivity,login::class.java))
                  finish()
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
                    finish()
                }
                else{
                    val i = it.exception?.message
                     progress.dismiss()
                    otppp.setText("")
                    notifyuserandretrydifferent(i)

                }
            }

    }

    private fun notifyuserandretrydifferent(i: String?) {
MaterialAlertDialogBuilder(this).apply {
    setMessage(i)
    setPositiveButton("RE-ENTER",DialogInterface.OnClickListener{dialog, which ->
        dialog.dismiss()


    })
    setNegativeButton("EDIT"){_,_ ->
        startActivity(Intent(this@otpActivity,login::class.java))
        finish()
    }
    create()
    show()
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
        istimeractive = true
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
