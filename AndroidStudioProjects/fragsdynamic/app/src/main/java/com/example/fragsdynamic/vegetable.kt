package com.example.fragsdynamic

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_vegetable.view.*

/**
 * A simple [Fragment] subclass.
 */
class vegetable : Fragment() {
    val veg = arrayOf("potato" , "carrot" , "reddish" , "a" , "b" , "c" , "D")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val frag = inflater.inflate(R.layout.fragment_vegetable, container, false)
        frag.vegetablelist.adapter = this.context?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.activity_list_item,
                android.R.id.text1,
                veg)
        }


        return frag
    }

}
