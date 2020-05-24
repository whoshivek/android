package com.shivek.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(this,roomdatabase::class.java, "user.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      button.setOnClickListener {
          GlobalScope.launch(Dispatchers.IO) {  db.userdo().insert(User("shivekkk" , 19, "budhvihar","95656532"))}
      }

               db.userdo().select().observe(this , Observer {l ->
                if (l.isNotEmpty()) {
                    with(l[l.size-1]) {
                        textView.text = name + id
                        textView2.text = address
                        textView3.text = age.toString()
                        textView4.text = number
                    }
            }





    } )
}
}
