package com.shivek.mymallfinal

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.shivek.mymallfinal.adapterandmodels.modelviewpager
import com.shivek.mymallfinal.adapterandmodels.productsdetails_vp
import com.shivek.mymallfinal.adapterandmodels.productviwpager
import kotlinx.android.synthetic.main.activity_productdetails.*
import kotlinx.android.synthetic.main.product_decription.*
import kotlinx.android.synthetic.main.product_first.*
import kotlinx.android.synthetic.main.product_first.view.*


class productdetails : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetails)

        val toolbar: Toolbar = findViewById(R.id.toolbar3)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
          supportActionBar?.setDisplayHomeAsUpEnabled(true)

val list = arrayListOf<modelviewpager>()
        val adapter = productviwpager(list)
        list.add(modelviewpager(banner = R.drawable.home))
        list.add(modelviewpager(banner = R.drawable.home))
        list.add(modelviewpager(banner = R.drawable.home))
        list.add(modelviewpager(banner = R.drawable.home))
        list.add(modelviewpager(banner = R.drawable.home))
           
        productview.adapter = adapter

         indicator.attachTo(productview)

        val fff = productsdetails_vp(supportFragmentManager , tab = 3)
        viewpagerproductdetails.adapter = fff
        tablayoutviewpager.setupWithViewPager(viewpagerproductdetails)
       /////////////////////////


        //////
        floatt.setOnClickListener {
            finish()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.searchandcart, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {

            R.id.menu_search3 ->
            {

            }
            android.R.id.home ->
            {
                finish()
                return true
            }
            R.id.menu_cart3->
            {

            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }
}


