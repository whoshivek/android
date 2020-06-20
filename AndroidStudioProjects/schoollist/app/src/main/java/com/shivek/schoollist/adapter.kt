package com.shivek.schoollist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv.view.*

class adapter (val list : List<dataclass>) : RecyclerView.Adapter<adapter.vholder>(){
    class vholder (itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(dataclass: dataclass) {
               with(itemView){
                   text.text = dataclass.school_name
               }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.vholder {
       return vholder(LayoutInflater.from(parent.context).inflate(R.layout.rv , parent , false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: adapter.vholder, position: Int) {
        holder.bind(list[position])
    }


}