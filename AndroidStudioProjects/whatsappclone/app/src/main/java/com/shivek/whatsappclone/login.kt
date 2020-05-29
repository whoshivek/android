package com.shivek.whatsappclone

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.text.style.StyleSpan
import androidx.core.text.HtmlCompat
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import kotlinx.android.synthetic.main.activity_main.*

class login: AppCompatActivity() {
    lateinit var phoneno : String
    lateinit var countrycode : String
    private val CREDENTIAL_PICKER_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hintrequest()

        enter.addTextChangedListener(object :TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkinput()
            }
        })

        next.setOnClickListener{
            checknumber()
        }



    }

    private fun hintrequest() {
        val hint = HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()

        val credentail = Credentials.getClient(this)
        val intent = credentail.getHintPickerIntent(hint)
        startIntentSenderForResult(
            intent.intentSender,
            CREDENTIAL_PICKER_REQUEST,
            null,0,0,0
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            CREDENTIAL_PICKER_REQUEST ->
                if (resultCode == Activity.RESULT_OK && data != null)
                {
                    val credential = data.getParcelableExtra<Credential>(Credential.EXTRA_KEY)

                    val c = credential.id.substring(credential.id.length-10)
                    enter.setText(c)
                }
        }
    }

    private fun checknumber() {
        countrycode = cpp.selectedCountryCodeWithPlus
        phoneno = countrycode + enter.text.toString()


        alerbox()
    }

    private fun alerbox() {

        MaterialAlertDialogBuilder(this).apply {
            val span = SpannableString(getString(R.string.wewill,phoneno))
            val StyleSpan = StyleSpan(Typeface.BOLD)
            span.setSpan(StyleSpan,35, span.length-49,Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            setMessage(span)
            setPositiveButton("OK"){_,_  ->
            showactivity()}
            setNegativeButton("EDIT"){ dialog, which ->

                dialog.dismiss()
            }
            setCancelable(false)
            create()
            show()
        }
    }

    private fun showactivity() {

                    startActivity(Intent(this,otpActivity::class.java).putExtra(PHONE_NO , phoneno))
        finish()

    }

    private fun checkinput() {
        if (enter.text.toString().isNotEmpty())
        {
            if (!enter.text.toString().contains("+"))
            {if (enter.text.toString().length==10)
            {
                next.isEnabled = true
            }
            else
            {
                next.isEnabled = false
            }

            }
            else
            {
                next.isEnabled = false
            }

        }else
        {
            next.isEnabled = false
        }
    }
}