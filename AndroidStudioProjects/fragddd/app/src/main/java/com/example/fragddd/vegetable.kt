package com.example.fragddd

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val i= inflater.inflate(R.layout.fragment_vegetable, container, false)
    i.h.adapter = context?.let {
        ArrayAdapter<String>(
            it,
        R.layout.jnc,
        R.id.m,
            arrayOf("hj" , "h" , "gg" , "jj" , "ghg" , "ghg")
    )
    }


    return i;
    }


}
