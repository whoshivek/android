package com.shivek.mymallfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivek.mymallfinal.adapterandmodels.mycartadapter
import com.shivek.mymallfinal.adapterandmodels.mycartmodel
import kotlinx.android.synthetic.main.fragment_mycart.view.*


class mycart : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_mycart, container, false)
              val list = arrayListOf<mycartmodel>()
        list.add(mycartmodel(type = 0, name = "MI 5", image = R.drawable.home ,price = "Rs.78999/-"))
        list.add(mycartmodel(type = 1, name = "MI 5", image = R.drawable.home ,price = "Rs.78999/-"))
        list.add(mycartmodel(type = 1, name = "MI 5", image = R.drawable.home ,price = "Rs.78999/-"))
        list.add(mycartmodel(type = 1, name = "MI 5", image = R.drawable.home ,price = "Rs.78999/-"))
        list.add(mycartmodel(type = 1, name = "MI 5", image = R.drawable.home ,price = "Rs.78999/-", delivert = "paid",totalamount = "544/-"))

        val adapter = context?.let { mycartadapter(it,list) }
v.rvmycart.layoutManager = LinearLayoutManager(activity)
        v.rvmycart.adapter =adapter
        return v

    }


}