package com.shivek.wayezischoolcomparison

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.splashscreen.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signup.setOnClickListener {
            startActivity(Intent(this , MainActivity2::class.java))
            Animatoo.animateSwipeLeft(this)
            finish()
        }

//this.overridePendingTransition(R.anim.anim, R.anim.anim)
  //      startActivity(Intent(this , MainActivity2::class.java))
    //    val topanim = AnimationUtils.loadAnimation(this , R.anim.anim)
      //  buss.animation = topanim


    }
}

