package com.shivek.ttt.adaptersandmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manish.pdfreaderrr.R
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import kotlinx.android.synthetic.main.gridrv.view.*
import kotlinx.android.synthetic.main.rv_category.view.*

class gridadapter(val list : List<viewpagermodel>, val click : (viewpagermodel)->Unit, val clickkk : (viewpagermodel)->Unit
                  , val clickkkk: (viewpagermodel) -> Unit) : RecyclerView.Adapter<gridadapter.lholder>()
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
              Glide.with(context).load(viewpagermodel.banner).into(categoryimagge)
                categorytextt.text = viewpagermodel.vname
                itemView.setOnClickListener {
                    clickkkk(viewpagermodel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): lholder {
        return  lholder(LayoutInflater.from(parent.context).inflate(R.layout.gridrv , parent , false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: gridadapter.lholder, position: Int) {
        holder.bind(list[position] , click , clickkk , clickkkk)
    }


}