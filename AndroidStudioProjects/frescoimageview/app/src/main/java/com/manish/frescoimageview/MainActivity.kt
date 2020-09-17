package com.manish.frescoimageview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recyclerviewcontainer.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = arrayListOf<list>()
        val adapter = Recycleradapter(list)

//Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/twilightscans-9660c.appspot.com/o/A%20Strange%20Worldchapter-1%2Fc3.jpg?alt=media&token=4fcb5b1c-caf3-4783-80b2-67ab60761210").networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(zoomage)
list.add(list(image = "https://firebasestorage.googleapis.com/v0/b/twilightscans-9660c.appspot.com/o/A%20Strange%20Worldchapter-1%2Fc3.jpg?alt=media&token=4fcb5b1c-caf3-4783-80b2-67ab60761210"))
        list.add(list(image = "https://firebasestorage.googleapis.com/v0/b/twilightscans-9660c.appspot.com/o/A%20Strange%20Worldchapter-1%2Fc2.jpg?alt=media&token=5ff4ce78-05d3-48ca-a840-3768717dfa0b"))
         recyclerview.layoutManager = LinearLayoutManager(this)
       recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()

    }
}