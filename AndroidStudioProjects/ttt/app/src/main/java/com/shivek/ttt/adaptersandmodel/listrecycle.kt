package com.shivek.ttt.adaptersandmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import com.shivek.ttt.R
import kotlinx.android.synthetic.main.listrecycler.view.*

class listrecycle(val list : List<viewpagermodel> , val click : (viewpagermodel)->Unit) : RecyclerView.Adapter<listrecycle.lholder>()
{
    class lholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            viewpagermodel: viewpagermodel,
            click: (viewpagermodel) -> Unit
        ) {
           with(itemView)
           {
               Glide.with(itemView).load(viewpagermodel.banner).into(listimage)
               ltitle.text = viewpagermodel.vname
               lbutton1.setText(viewpagermodel.b1)
               lbutton2.setText(viewpagermodel.b2)
               ldate1.text = viewpagermodel.b1date
               ldate2.text = viewpagermodel.b2date
             lbutton1.setOnClickListener {
                 click(viewpagermodel)
             }
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listrecycle.lholder {
      return  lholder(LayoutInflater.from(parent.context).inflate(R.layout.listrecycler , parent , false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: listrecycle.lholder, position: Int) {
        holder.bind(list[position] , click)
    }


}