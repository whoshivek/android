package com.shivek.multipleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.rvmaterial.view.*

class gridadapter(val list: List<commonmodel>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var View : View? = null
        if (convertView==null)
        {

            if (parent != null) {
                View = LayoutInflater.from(parent.context).inflate(R.layout.rvmaterial , null)
                View.texttt.text = list[position].text
                list[position].image?.let { View?.imageee?.setImageResource(it) }
            }
        }
        else{
            View = convertView
        }
        return View

    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return  4
    }


}
