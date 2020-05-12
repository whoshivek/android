package com.example.zerokaatanew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        o.setOnClickListener(
            {
                Toast.makeText(this,"PLAYER O TURNS" , Toast.LENGTH_SHORT).show()
                val c =Intent(this,Main3Activity::class.java)
                startActivity(c)
            }
        )

        x.setOnClickListener(
            {
                Toast.makeText(this,"PLAYER X TURNS" , Toast.LENGTH_SHORT).show()
                 val d = Intent(this,Main4Activity::class.java)
                startActivity(d)
            }
        )

        back.setOnClickListener(
            {
                val c = Intent(this , MainActivity::class.java)
                startActivity(c)
            }
        )



    }
}
