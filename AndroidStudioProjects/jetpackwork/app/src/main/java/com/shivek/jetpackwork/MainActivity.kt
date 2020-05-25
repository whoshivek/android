package com.shivek.jetpackwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val list = arrayListOf<Response>()
    val adapter = adapterr(list)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchdata()
    }

    private fun fetchdata() {
        val worker = OneTimeWorkRequestBuilder<worker>().build()

        WorkManager.getInstance(this).enqueue(worker)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }
}
