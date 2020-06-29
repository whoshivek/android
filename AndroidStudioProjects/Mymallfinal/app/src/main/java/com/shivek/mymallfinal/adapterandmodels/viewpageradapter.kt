package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.vp_bannerslider.view.*

class viewpageradapter(val list: ArrayList<modelviewpager>): RecyclerView.Adapter<viewpageradapter.viewholder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.vp_bannerslider,
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
        fun bind(modelviewpager: modelviewpager) {
                with(itemView)
                {

                    modelviewpager.banner?.let { banner_slide.setImageResource(it) }
                }
        }

    }

}

