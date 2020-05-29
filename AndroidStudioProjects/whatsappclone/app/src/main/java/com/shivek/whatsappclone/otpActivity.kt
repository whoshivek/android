package com.shivek.whatsappclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_otp.*

const val PHONE_NO = "phone no."

class otpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)


        val phoneno = intent.getStringExtra(PHONE_NO)
        verifyno.text = "VERIFY $phoneno"

        span()
        countertimer(6000)

    }

    private fun countertimer(timeinmillfuture: Long) {
        resend.isEnabled = true
        object : CountDownTimer(timeinmillfuture,1000){
            override fun onFinish() {
               resend.isEnabled = true
            }

            override fun onTick(millisUntilFinished: Long) {

              counter.text = "Seconds Remaining $timeinmillfuture/1000"
            }
        }

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
}
