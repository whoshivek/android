package com.shivek.pubg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.storage.FirebaseStorage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val user = FirebaseStorage.getInstance().getReference()


        user.child("").downloadUrl.addOnCompleteListener {
            if (it.isSuccessful)
            {
                downloadfiles()

            }

        }
    }

    private fun downloadfiles() {

    }
}