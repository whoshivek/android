package com.shivek.jetpackwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rvvv.view.*

class adapterr(val list : List<Response>) : RecyclerView.Adapter<adapterr.rholder>()
{
    class rholder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rholder {
     return rholder(LayoutInflater.from(parent.context).inflate(R.layout.rvvv, parent,false))
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: rholder, position: Int) {
             holder.itemView.tvv.text = list[position].current?.tempC.toString()
    }

}