package com.shivek.ttt.adaptersandmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import com.shivek.ttt.R
import kotlinx.android.synthetic.main.chapterlistrv.view.*

class chaperlist (val list : List<viewpagermodel>, val click : (viewpagermodel)-> Unit) : RecyclerView.Adapter<chaperlist.cholder>()
{
    class cholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            any: viewpagermodel,
            click: (viewpagermodel) -> Unit
        ) {
with(itemView)
{
    textvv.text = "${any.vname} ${any.vdate}"
    itemView.setOnClickListener {
        click(any)
    }
}
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chaperlist.cholder {
        return cholder(LayoutInflater.from(parent.context).inflate(R.layout.chapterlistrv,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: chaperlist.cholder, position: Int) {
        holder.bind(list[position],click)
    }

}