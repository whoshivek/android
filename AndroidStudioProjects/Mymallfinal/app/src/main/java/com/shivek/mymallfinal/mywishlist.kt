package com.shivek.mymallfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivek.mymallfinal.adapterandmodels.mywishlistadapter
import com.shivek.mymallfinal.adapterandmodels.mywishlistmodel
import kotlinx.android.synthetic.main.fragment_mywishlist.view.*


class mywishlist : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_mywishlist, container, false)
             val list = arrayListOf<mywishlistmodel>()
        val adapter = mywishlistadapter(list)
        list.add(mywishlistmodel(name = "Iphone 11 pro" , image = R.drawable.trash,price = "Rs.632132/-"))
        list.add(mywishlistmodel(name = "Iphone 11 pro" , image = R.drawable.trash,price = "Rs.632132/-"))
        list.add(mywishlistmodel(name = "Iphone 11 pro" , image = R.drawable.trash,price = "Rs.632132/-"))
        list.add(mywishlistmodel(name = "Iphone 11 pro" , image = R.drawable.trash,price = "Rs.632132/-"))
        list.add(mywishlistmodel(name = "Iphone 11 pro" , image = R.drawable.trash,price = "Rs.632132/-"))

        v.mywishlistadapterrv.layoutManager =LinearLayoutManager(activity)
        v.mywishlistadapterrv.adapter = adapter
        return v
    }


}