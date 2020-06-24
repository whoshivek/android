package com.shivek.mymallfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivek.mymallfinal.adapterandmodels.adapterspecification
import com.shivek.mymallfinal.adapterandmodels.modelspecification
import com.shivek.mymallfinal.adapterandmodels.subheading
import kotlinx.android.synthetic.main.fragment_productspecification.view.*
import kotlinx.android.synthetic.main.product_first.*


class productspecification : Fragment() {


    val list = arrayListOf<modelspecification>()
    val adapter = adapterspecification(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sublist = arrayListOf<subheading>()
        sublist.add(subheading(topic = "ram ",title = "8gb"))
        sublist.add(subheading(topic = "ram ",title = "84gb"))
        sublist.add(subheading(topic = "ram ",title = "8gb"))
        sublist.add(subheading(topic = "ram ",title = "80gb"))
        val sublist1 = arrayListOf<subheading>()
        sublist1.add(subheading(topic = "ram ",title = "80gb"))
        sublist1.add(subheading(topic = "ram ",title = "80gb"))
        val sublist3 = arrayListOf<subheading>()
        sublist3.add(subheading(topic = "ram ",title = "16gb"))
        sublist3.add(subheading(topic = "ram ",title = "24gb"))

        list.add(modelspecification(heading = "general" , subdata = sublist ))
        list.add(modelspecification(heading = "generalgg" , subdata = sublist1 ))
        list.add(modelspecification(heading = "generaldf" , subdata = sublist ))
        list.add(modelspecification(heading = "generalvf" , subdata = sublist3 ))
        list.add(modelspecification(heading = "generalhg" , subdata = sublist ))



        val v =  inflater.inflate(R.layout.fragment_productspecification, container, false)
             v.productspecsrv.layoutManager = LinearLayoutManager(activity )
        v.productspecsrv.adapter = adapter
        adapter.notifyDataSetChanged()
        return v
    }


}