package com.example.fragmentsstat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_down2.*
import kotlinx.android.synthetic.main.fragment_down2.view.*

/**
 * A simple [Fragment] subclass.
 */
class down : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var count=0
        val frag = inflater.inflate(R.layout.fragment_down2, container, false)
        // Inflate the layout for this fragment
        frag.down.setOnClickListener(
            {
                count--
                tdown.text = count.toString()
            }
        )

        return frag
    }

}
