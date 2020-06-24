package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.rv_specs.view.*

class subadapter (val list2 : modelspecification ):RecyclerView.Adapter<subadapter.subholder>(){
    class subholder(itemView : View):RecyclerView.ViewHolder(itemView) {
        fun bind(subheading: subheading) {
            with(itemView)
            {
                tt1.text = subheading.title
                tt2.text = subheading.topic
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): subadapter.subholder {
        return subholder(LayoutInflater.from(parent.context).inflate(R.layout.rv_specs , parent,false))
    }

    override fun getItemCount(): Int {
      return list2.subdata.size
    }

    override fun onBindViewHolder(holder: subadapter.subholder, position: Int) {
        holder.bind(list2.subdata[position])
    }


}