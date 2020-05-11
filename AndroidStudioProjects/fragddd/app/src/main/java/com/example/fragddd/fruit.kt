package com.example.fragddd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_fruit.view.*

/**
 * A simple [Fragment] subclass.
 */
class fruit : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val i=inflater.inflate(R.layout.fragment_fruit, container, false)

        i.lff.adapter = context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.jnc,
                R.id.m,
                arrayOf("ggjg" , "jgg" , "hjhjg" , "njhu" , "bjhjh")
            )
        }



    return i;
    }


}
