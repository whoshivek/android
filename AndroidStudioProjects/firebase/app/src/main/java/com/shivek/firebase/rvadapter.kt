package com.shivek.firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rvadapter.view.*

class rvadapter (val list: List<FIREbase>) : RecyclerView.Adapter<rvadapter.rvholder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvholder {
        return rvholder(LayoutInflater.from(parent.context).inflate(R.layout.rvadapter,parent,false))
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: rvholder, position: Int) {

           holder.itemView.t1.text = list[position].notes
    }

    class rvholder(itemView: View ) : RecyclerView.ViewHolder(itemView) {

    }

}