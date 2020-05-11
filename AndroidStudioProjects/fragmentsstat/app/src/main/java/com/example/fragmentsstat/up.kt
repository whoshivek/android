package com.example.fragmentsstat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_up.*
import kotlinx.android.synthetic.main.fragment_up.view.*

/**
 * A simple [Fragment] subclass.
 */
class up : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var count =0
        val frag = inflater.inflate(R.layout.fragment_up, container, false)
        // Inflate the layout for this fragment

        frag.add.setOnClickListener(
            {
                count++
                tadd.text = count.toString()
            }
        )
        return frag
    }

}
