package com.shivek.mymallfinal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivek.mymallfinal.adapterandmodels.myorderadapter
import com.shivek.mymallfinal.adapterandmodels.myordermodel
import kotlinx.android.synthetic.main.fragment_myorder.view.*


class myorderfragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v=inflater.inflate(R.layout.fragment_myorder, container, false)
        val list = arrayListOf<myordermodel>()
        val adaper = myorderadapter(list ,{myordermodel ->click(myordermodel)  })

        list.add(myordermodel(name = "MI" , image = R.drawable.home))
        list.add(myordermodel(name = "MIs" , image = R.drawable.home))
        list.add(myordermodel(name = "MIfsfs" , image = R.drawable.home))
        list.add(myordermodel(name = "apple" , image = R.drawable.trash))
        list.add(myordermodel(name = "MI" , image = R.drawable.home))
        list.add(myordermodel(name = "MI" , image = R.drawable.home))
v.myorderrv.layoutManager = LinearLayoutManager(activity)
        v.myorderrv.adapter = adaper
        return v

    }

    private fun click(myordermodel: myordermodel) {
               startActivity(Intent(activity , orderdetails::class.java))
    }


}