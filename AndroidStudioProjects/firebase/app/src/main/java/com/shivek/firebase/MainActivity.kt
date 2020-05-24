package com.shivek.firebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.*
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    val list = arrayListOf<FIREbase>()
    val adapter = rvadapter(list)

    fun funnn() {
        add.setOnClickListener {
            val c = e1.text.toString()
            FirebaseDatabase.getInstance().getReference().child("notes").push().setValue(c)
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = adapter
            FirebaseDatabase.getInstance().getReference().child("notes")
                .addChildEventListener(object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onChildMoved(p0: DataSnapshot, p1: String?) {

                    }

                    override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                    }

                    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                        val c = p0.getValue(String::class.java)
                        c?.let { it1 -> FIREbase(it1) }?.let { it2 -> list.add(it2) }
                        adapter.notifyDataSetChanged()

                    }

                    override fun onChildRemoved(p0: DataSnapshot) {

                    }
                })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val i = FirebaseAuth.getInstance().currentUser

        if (i != null) {
            funnn()
        } else {
            startActivityForResult(
                // Get an instance of AuthUI based on the default app
                AuthUI.getInstance().createSignInIntentBuilder().build(),
                123
            );
        }

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(
                    Arrays.asList(
                        GoogleBuilder().build(),
                        EmailBuilder().build(),
                        PhoneBuilder().build(),
                        AnonymousBuilder().build()
                    )
                )
                .build(),
            123
        )


    }
}





