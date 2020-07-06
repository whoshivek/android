package com.shivek.ttt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener

import com.google.firebase.storage.FirebaseStorage
import com.shivek.mymallfinal.adapterandmodels.categorymodel
import com.shivek.ttt.adaptersandmodel.GlideApp
import com.shivek.ttt.adaptersandmodel.chapteradapter
import kotlinx.android.synthetic.main.activity_chapter.*

import kotlinx.android.synthetic.main.chapterrv.*

var coins : Int = 500
var usedcoins : Int= 100

class chapter : AppCompatActivity() {

    val list = arrayListOf<categorymodel>()
    val adapter = chapteradapter(list)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
              val title = intent.getStringExtra("hi")
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
      supportActionBar?.setTitle(title)

        val chapter = intent.getStringExtra("hii")
        chaptername.text = chapter


        iOSDialogBuilder(this).apply {
            setTitle("Available Coins:${coins}")
            setSubtitle("This chapter will cost you ${usedcoins} Coins")
            setBoldPositiveLabel(false)
            setCancelable(false)
            setPositiveListener("PROCEED", iOSDialogClickListener {
                coins = coins - usedcoins
                
                it.dismiss()
            })
            setNegativeListener("COINS", iOSDialogClickListener {
                it.dismiss()
            })

        }
            .build()
            .show()























        val f = FirebaseStorage.getInstance().getReferenceFromUrl("gs://twilightscans-9660c.appspot.com/abc/c2.jpg")
      GlideApp.with(this).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).override(1600 ,30000).placeholder(R.drawable.loader).load(f).into(container)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home ->{
                startActivity(Intent(this , MainActivity2::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}