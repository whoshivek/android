package com.shivek.mymallfinal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.text.category

class HomeFragment : Fragment() {
    val list = arrayListOf<categorymodel>()
    val categoryadapter = com.shivek.mymallfinal.categoryadapter(list)

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
val view = inflater.inflate(R.layout.fragment_home, container, false)
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))
        list.add(categorymodel(text = "hello"))

        view.category.layoutManager = LinearLayoutManager(activity , OrientationHelper.HORIZONTAL , false)
        view.category.adapter = categoryadapter
        categoryadapter.notifyDataSetChanged()






        return view
    }


}