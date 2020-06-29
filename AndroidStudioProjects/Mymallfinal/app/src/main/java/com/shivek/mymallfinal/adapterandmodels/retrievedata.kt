package com.shivek.mymallfinal.adapterandmodels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.MainActivity2
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.activity_retrievedata.*

class retrievedata : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrievedata)

        val act = GoogleSignIn.getLastSignedInAccount(this)
        if (act!=null)
        {
            f1.setText(act.givenName)
            f2.setText(act.familyName)

        }

        save.setOnClickListener {
            startActivity(Intent(this , MainActivity2::class.java))
               val map = hashMapOf<String , Any>()
            map.put("fullname" , "${f1.text.toString()} ${f2.text.toString()}")

        }
    }
}