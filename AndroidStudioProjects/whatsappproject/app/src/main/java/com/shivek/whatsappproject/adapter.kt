package com.shivek.whatsappproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class adapter(val listadpter : List<data>) : RecyclerView.Adapter<adapter.tholder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.tholder {
        return tholder(LayoutInflater.from(parent.context).
        inflate(R.layout.list , parent , false))

    }

    override fun getItemCount(): Int {
       return listadpter.size
    }

    override fun onBindViewHolder(holder: adapter.tholder, position: Int) {
holder.bind(listadpter[position])
    }

    class tholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: data) {
            data.a

        }
    }

}