package com.shivek.ttt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.shivek.mymallfinal.adapterandmodels.categorymodel
import com.shivek.ttt.adaptersandmodel.GlideApp
import com.shivek.ttt.adaptersandmodel.chapteradapter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_chapter.*

import kotlinx.android.synthetic.main.chapterrv.*





class chapter : AppCompatActivity() {

    val list = arrayListOf<categorymodel>()
    val adapter = chapteradapter(list)

companion object{
    var coins : Int =0
    var usedcoins : Int= 100
}

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
        ///////////////////////
        


        val sp = getSharedPreferences("sp",Context.MODE_PRIVATE)
     coins=   sp.getInt("coin" , coins)


        iOSDialogBuilder(this).apply {
            setTitle("Available Coins:${coins}")

            setBoldPositiveLabel(false)
            setCancelable(false)
            if (coins>=100) {
                setSubtitle("This chapter will cost you ${usedcoins} Coins")
                setPositiveListener("PROCEED", iOSDialogClickListener {
                    val edit = sp.edit()
                    coins = coins - usedcoins
                    edit.putInt("coin", coins)
                    edit.apply()

                    it.dismiss()
                })
                setNegativeListener("BACK", iOSDialogClickListener {
                    it.dismiss()
                    finish()
                })

            }
            if (coins<100)
            { setSubtitle("Oops Insufficient Coins,100 coins Required")
                setPositiveListener("ADD COINS", {
                   startActivity(Intent(this@chapter,MainActivity2::class.java).putExtra("code",0))
                    finish()
                })
            }

        }
            .build()
            .show()

        FirebaseStorage.getInstance().getReference("${chapter}${title}").listAll()
            .addOnSuccessListener {
                 val e = it.items.size
                for (d in 1..e)
                {
                    list.add(categorymodel(link= "gs://twilightscans-9660c.appspot.com/${chapter}${title}/c${d}.jpg" as String) )
                    adapter.notifyDataSetChanged()
                }

            }





chapterrv.layoutManager = LinearLayoutManager(this)
        chapterrv.adapter = adapter
        adapter.notifyDataSetChanged()


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home ->{
                startActivity(Intent(this , MainActivity2::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        startActivity(Intent(this , MainActivity2::class.java))
        finish()
    }


}

