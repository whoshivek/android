package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.fragment_productspecification.view.*
import kotlinx.android.synthetic.main.rv_specs.view.*

class adapterspecification (val list : List<modelspecification>) : RecyclerView.Adapter<adapterspecification.sholder>()
{
    class sholder (itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(modelspecification: modelspecification) {
              with(itemView){
                  tt1.text = modelspecification.title
                  tt2.text = modelspecification.topic
              }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterspecification.sholder {
        return sholder(LayoutInflater.from(parent.context).inflate(R.layout.rv_specs , parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: adapterspecification.sholder, position: Int) {
        holder.bind(list[position])
    }


}