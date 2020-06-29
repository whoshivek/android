package com.shivek.multipleview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.deall.*
import kotlinx.android.synthetic.main.grid.*

class MainActivity : AppCompatActivity() {

///////////////////////////////settingdeal/////////////////////////
val deallist = arrayListOf<commonmodel>()
    val dealadapter = com.shivek.multipleview.dealadapter(deallist)
    //////////////////////////////////////////////////
val gridlist = arrayListOf<commonmodel>()
    val gridadapter = com.shivek.multipleview.gridadapter(gridlist)

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ////////////////////////////////////////////////////////////
        deallist.add(commonmodel(R.drawable.ic_launcher_background,"hi"))
        deallist.add(commonmodel(R.drawable.ic_launcher_background,"hi"))
        deallist.add(commonmodel(R.drawable.ic_launcher_background,"hi"))
        deallist.add(commonmodel(R.drawable.ic_launcher_background,"hi"))

        dealtext.text = "deals of the day"
        dealrv.layoutManager =LinearLayoutManager(this , OrientationHelper.HORIZONTAL,false)
        dealrv.adapter = dealadapter

        //////////////////////////////////////////////////////////////////////////////////////
        gridlist.add(commonmodel(R.drawable.ic_launcher_background,"hello"))
        gridlist.add(commonmodel(R.drawable.ic_launcher_background,"hello"))
        gridlist.add(commonmodel(R.drawable.ic_launcher_background,"hello"))
        gridlist.add(commonmodel(R.drawable.ic_launcher_background,"hello"))
        gridlist.add(commonmodel(R.drawable.ic_launcher_background,"hello"))

        gridlist.add(commonmodel(R.drawable.ic_launcher_background,"hello"))
gridtext.text = "trending"
gridrv.adapter = gridadapter

        ////////////////////////////////////////////////////////////////////////////////////////////////

        val testlist = arrayListOf<homepagemodel>()
        val testadapter = homeadapter(testlist)

        testlist.add(homepagemodel(0,"hello",deallist))
        testlist.add(homepagemodel(1,"hello",deallist))
        testlist.add(homepagemodel(0,"hello",deallist))

        test.layoutManager = LinearLayoutManager(this)
        test.adapter = testadapter
        testadapter.notifyDataSetChanged()


    }
}