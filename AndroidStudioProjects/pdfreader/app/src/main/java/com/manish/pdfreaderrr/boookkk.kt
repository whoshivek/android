package com.manish.pdfreaderrr

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.blue
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.manish.pdfreaderrr.R
import com.shivek.ttt.adaptersandmodel.productviewpager_vp
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_boookkk.*

class boookkk : AppCompatActivity() {
    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boookkk)




        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)



val ct : CollapsingToolbarLayout = findViewById(R.id.toolbar_layout)

        val fff = productviewpager_vp(supportFragmentManager , tab = 2)
        viewpagerproductdetails.adapter = fff
        tablayoutviewpager.setupWithViewPager(viewpagerproductdetails)
        val c = intent?.getStringExtra("bookname")

        if (c != null) {
            FirebaseFirestore.getInstance().collection("lastadapter").document(c).get()
                .addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        Glide.with(this).load(it.result.get("comichalfcover")).into(halfimage)
                                val bookchapter = it.result.get("lastchapternumber?")
                        val image = it.result.get("comicimage") as String
                        readlatest.setOnClickListener {
                            startActivity(Intent(this,chapter::class.java).putExtra("hii",c)
                                .putExtra("hi","chaper-${bookchapter}").putExtra("image", image))
                        }

                    }
                    else
                    {
                        val s = it.exception?.message
                        if (s != null) {
                            Toasty.error(this,s,Toasty.LENGTH_SHORT).show()
                        }
                    }
                }
        }




    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            android.R.id.home->{

                onBackPressed()
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}