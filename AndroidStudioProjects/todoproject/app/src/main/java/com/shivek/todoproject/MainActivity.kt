package com.shivek.todoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val list = arrayListOf<user>()
    var adapter = radapter(list)


    val db by lazy {

       dbclass.getDatabase(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        db.userdao().select().observe(this , Observer {
            if (!it.isNullOrEmpty())
            {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }


        })

        b1.setOnClickListener {

            Toast.makeText(this,"task added",Toast.LENGTH_SHORT).show()

            setdata()
        }



    }

    private fun setdata() {

        val c = e1.text.toString()


GlobalScope.launch(Dispatchers.Main) {
    val id = withContext(Dispatchers.IO){
       return@withContext db.userdao().insert(user(c))
    }
}

    }
}

