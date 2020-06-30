package com.shivek.multipleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewpagerrv.view.*

class viewpageradapter(val list : List<viewpagermodel>) : RecyclerView.Adapter<viewpageradapter.viewholder>(){
    class viewholder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        fun bind(viewpagermodel: viewpagermodel) {
                with(itemView){
                    viewpagerimage.setImageResource(viewpagermodel.viewimage)

                }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewpageradapter.viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.viewpagerrv , parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewpageradapter.viewholder, position: Int) {
        holder.bind(list[position])
    }


}