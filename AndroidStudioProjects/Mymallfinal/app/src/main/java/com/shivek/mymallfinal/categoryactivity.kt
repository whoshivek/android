package com.shivek.mymallfinal

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.shivek.mymallfinal.adapterandmodels.*
import kotlinx.android.synthetic.main.activity_categoryactivity.*

class categoryactivity : AppCompatActivity() {
    val list = arrayListOf<categorymodel>()
    val vplist = arrayListOf<viewpagermodel>()
    val dlist = arrayListOf<commonmodel>()
    val gadapter = gridadapter(dlist)
    val dadapter = dealadapter(dlist )

    private fun clickk(commonmodel: commonmodel) {
             finish()

    }

    val vpadapter =
        viewpageradapter(vplist)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoryactivity)

        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        val get = intent.getStringExtra("cat")
        supportActionBar?.title = get
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


vplist.add(viewpagermodel(banner = "https://firebasestorage.googleapis.com/v0/b/mymallfinal.appspot.com/o/banners%2Fd764f77c-f703-4a1d-ae4b-2359cd57bb941593175356429-Biba_App.jpg?alt=media&token=ed6f7675-b87a-4b98-b3d5-65d8c15a15e8"))

        dlist.add(
            commonmodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi2",
                text2 = "bye2",
                text3 = "gg2"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi5",
                text2 = "bye5",
                text3 = "gg5"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi2",
                text2 = "bye2",
                text3 = "gg2"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi5",
                text2 = "bye5",
                text3 = "gg5"
            )
        )

        gridlayout2.adapter = gadapter
        gadapter.notifyDataSetChanged()
        dealsoftheday2.layoutManager = LinearLayoutManager(this , OrientationHelper.HORIZONTAL ,false)
        dealsoftheday2.adapter = dadapter
        dadapter.notifyDataSetChanged()
       // bannerslider2.adapter = vpadapter
        bannerslider2.beginFakeDrag()
        vpadapter.notifyDataSetChanged()


        

}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {

            R.id.menu_search2 ->
            {

            }
            android.R.id.home ->
            {
                finish()
                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }

}