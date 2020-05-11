package com.example.fragsdynamic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_fruits.view.*

/**
 * A simple [Fragment] subclass.
 */
class fruits : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        val frag = inflater.inflate(R.layout.fragment_fruits, container, false)




        return frag


    }
}
