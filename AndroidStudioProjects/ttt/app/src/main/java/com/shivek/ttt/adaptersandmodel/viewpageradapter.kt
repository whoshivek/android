package com.shivek.mymallfinal.adapterandmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.shivek.ttt.R

import kotlinx.android.synthetic.main.viewpagerrv.view.*

class viewpageradapter(val list : List<viewpagermodel>): RecyclerView.Adapter<viewpageradapter.viewholder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.viewpagerrv,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(list[position])
    }
    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(modelviewpager: viewpagermodel) {
            with(itemView)
            {
                bb.setText(modelviewpager.b1)
                bbb.setText(modelviewpager.b2)
              vname.text = modelviewpager.vname
                vdate.text = modelviewpager.vdate
               Glide.with(this).load(modelviewpager.banner).into(imageviewpager)
            }
        }

    }

}


