package com.manish.pdfreaderrr

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manish.pdfreaderrr.R
import kotlinx.android.synthetic.main.fragment_home.view.*


class Aboutus : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_aboutus, container, false)

        v.discord.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://discord.com/invite/eH4y7eZ")
            startActivity(i)
        }



        return v

    }
}