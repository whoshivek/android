package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.mywishlistrv.view.*

class mywishlistadapter (val list : List<mywishlistmodel>) : RecyclerView.Adapter<mywishlistadapter.mywisholder>(){
    class mywisholder (itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bind(mywishlistmodel: mywishlistmodel) {
          with(itemView){
              rvcartname.text = mywishlistmodel.name
              rvcartprice.text = mywishlistmodel.price
              mywishlistmodel.image?.let { rvimage.setImageResource(it) }
          }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): mywishlistadapter.mywisholder {
        return mywisholder(LayoutInflater.from(parent.context).inflate(R.layout.mywishlistrv , parent,false
        ))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: mywishlistadapter.mywisholder, position: Int) {
        holder.bind(list[position])

    }

}