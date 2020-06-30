package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.rvmaterial.view.*

class gridadapter(val list: List<commonmodel>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var View : View? = null
      if (convertView==null)
      {
            View = LayoutInflater.from(parent?.context).inflate(R.layout.rvmaterial, null)
             View.dealtext3.text = list[position].text1
          View.dealtext2.text = list[position].text2
          list[position].image?.let { View?.dothdayimage?.setImageResource(it) }
          View.dealtext1.text = list[position].text1

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
