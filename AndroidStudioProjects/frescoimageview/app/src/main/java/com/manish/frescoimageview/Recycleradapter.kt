package com.manish.frescoimageview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.recyclerviewcontainer.view.*
import java.util.zip.Inflater

class Recycleradapter(val list : List<list>) : RecyclerView.Adapter<Recycleradapter.vholder>()
{
    class vholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(list: list) {
                  with(itemView)
                  {
                      Picasso.get().load(list.image).into(zoomageg)
                  }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycleradapter.vholder {
        return vholder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewcontainer , parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Recycleradapter.vholder, position: Int) {
    holder.bind(list[position])

    }

}