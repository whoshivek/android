package com.shivek.multipleview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adbanner.*
import kotlinx.android.synthetic.main.deall.*
import kotlinx.android.synthetic.main.grid.*
import kotlinx.android.synthetic.main.viewpager.*

class MainActivity : AppCompatActivity() {

///////////////////////////////settingdeal/////////////////////////
val deallist = arrayListOf<commonmodel>()
    val dealadapter = com.shivek.multipleview.dealadapter(deallist)
    //////////////////////////////////////////////////
val gridlist = arrayListOf<commonmodel>()
    val gridadapter = com.shivek.multipleview.gridadapter(gridlist)
    ////////////////////////////////////////////////////////////////
    val viewlist = arrayListOf<viewpagermodel>()
    val viewpageradapterr = viewpageradapter(viewlist)
////////////////////////////////////////////////////
    val adlist = arrayListOf<viewpagermodel>()
    val adadapter = adbaneeradapter(adlist)

    val adbannnerlist = arrayListOf<viewpagermodel>()
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
    viewlist.add(viewpagermodel(R.drawable.ic_launcher_background))
viewlist.add(viewpagermodel(R.drawable.ic_launcher_foreground))
    viewlist.add(viewpagermodel(R.drawable.ic_launcher_background))
    viewlist.add(viewpagermodel(R.drawable.ic_launcher_foreground))
    viewlist.add(viewpagermodel(R.drawable.ic_launcher_background))
    viewlist.add(viewpagermodel(R.drawable.ic_launcher_foreground))
    viewlist.add(viewpagermodel(R.drawable.ic_launcher_background))
    viewlist.add(viewpagermodel(R.drawable.ic_launcher_foreground))


    viewpagerrv.adapter = viewpageradapterr
    viewpageradapterr.notifyDataSetChanged()

    ////////////////////////////////////////////////////////////////////////////

        adlist.add(viewpagermodel(R.drawable.ic_launcher_foreground))

        adbannerrv.layoutManager = LinearLayoutManager(this)
        adbannerrv.adapter = adadapter
        adadapter.notifyDataSetChanged()


        ///////////////////////////////////////////////////////////////////

        val testlist = arrayListOf<homepagemodel>()
        val testadapter = homeadapter(testlist)

        testlist.add(homepagemodel(0,"hello",deallist))
        testlist.add(homepagemodel(1,"hello",deallist))
        testlist.add(homepagemodel(0,"hello",deallist))
    testlist.add(homepagemodel(2 , viewlist))
    testlist.add(homepagemodel(2 , viewlist))
    testlist.add(homepagemodel(2 , viewlist))
        testlist.add(homepagemodel(3 , adlist))
        testlist.add(homepagemodel(3 , adlist))
        testlist.add(homepagemodel(3 , adlist))


        test.layoutManager = LinearLayoutManager(this)
        test.adapter = testadapter
        testadapter.notifyDataSetChanged()


    }
}