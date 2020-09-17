package com.shivek.ttt.adaptersandmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manish.pdfreaderrr.R
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import kotlinx.android.synthetic.main.listrecycler.view.*

class listrecycle(val list : List<viewpagermodel> , val click : (viewpagermodel)->Unit , val clickkk : (viewpagermodel)->Unit
,val clickkkk: (viewpagermodel) -> Unit) : RecyclerView.Adapter<listrecycle.lholder>()
{
    class lholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            viewpagermodel: viewpagermodel,
            click: (viewpagermodel) -> Unit,
            clickkk: (viewpagermodel) -> Unit,
            clickkkk: (viewpagermodel) -> Unit
        ) {
           with(itemView)
           {
               Glide.with(itemView).load(viewpagermodel.banner).into(listimage)
               ltitle.text = viewpagermodel.vname
               lbutton1.setText(viewpagermodel.b1)
               lbutton2.setText(viewpagermodel.b2)
               if (lbutton2.text.isNullOrBlank())
               {
                   lbutton2.visibility = View.GONE
               }
               if (ltitle.text.isNullOrBlank())
               {
                   mainn.visibility = View.GONE
               }
             lbutton1.setOnClickListener {
                 click(viewpagermodel)
             }
               lbutton2.setOnClickListener {
                   clickkk(viewpagermodel)
               }
               itemView.setOnClickListener {
                   clickkkk(viewpagermodel)
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
        holder.bind(list[position] , click , clickkk , clickkkk)
    }


}