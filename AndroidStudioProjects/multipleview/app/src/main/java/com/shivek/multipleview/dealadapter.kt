package com.shivek.multipleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rvmaterial.view.*

class dealadapter (val list: List<commonmodel>): RecyclerView.Adapter<dealadapter.dealholder>()
{
    class dealholder(itemView:View) :RecyclerView.ViewHolder(itemView) {
        fun bind(commonmodel: commonmodel) {
               with(itemView){
                   commonmodel.image?.let { imageee.setImageResource(it) }
                   texttt.text = commonmodel.text


               }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dealadapter.dealholder {
        return dealholder(LayoutInflater.from(parent.context).inflate(R.layout.rvmaterial ,parent , false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: dealadapter.dealholder, position: Int) {
        holder.bind(list[position])
    }


}