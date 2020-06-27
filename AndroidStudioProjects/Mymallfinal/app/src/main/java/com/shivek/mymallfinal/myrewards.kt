package com.shivek.mymallfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivek.mymallfinal.adapterandmodels.myrewardsadapter
import com.shivek.mymallfinal.adapterandmodels.myrewardsmodel
import kotlinx.android.synthetic.main.fragment_myrewards.view.*
import kotlinx.android.synthetic.main.fragment_mywishlist.view.*


class myrewards : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_myrewards, container, false)
        val list = arrayListOf<myrewardsmodel>()
        val adapter = myrewardsadapter(list)
        list.add(myrewardsmodel("cashback","till 2nd may 2018" , " bella via cjje jfbejf fjnefj ejfbejf efjbef jfbej jbefj "))
        list.add(myrewardsmodel("cashback","till 2nd may 2018" , " bella via cjje jfbejf fjnefj ejfbejf efjbef jfbej jbefj "))
        list.add(myrewardsmodel("cashback","till 2nd may 2018" , " bella via cjje jfbejf fjnefj ejfbejf efjbef jfbej jbefj "))
        list.add(myrewardsmodel("cashback","till 2nd may 2018" , " bella via cjje jfbejf fjnefj ejfbejf efjbef jfbej jbefj "))
        list.add(myrewardsmodel("cashback","till 2nd may 2018" , " bella via cjje jfbejf fjnefj ejfbejf efjbef jfbej jbefj "))
        list.add(myrewardsmodel("cashback","till 2nd may 2018" , " bella via cjje jfbejf fjnefj ejfbejf efjbef jfbej jbefj "))
        list.add(myrewardsmodel("cashback","till 2nd may 2018" , " bella via cjje jfbejf fjnefj ejfbejf efjbef jfbej jbefj "))
v.myrewardstv.layoutManager =LinearLayoutManager(activity)
        v.myrewardstv.adapter =adapter
        return v

    }


}