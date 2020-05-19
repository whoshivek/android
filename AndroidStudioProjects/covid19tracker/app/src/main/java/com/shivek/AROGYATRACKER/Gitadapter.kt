package com.shivek.AROGYATRACKER

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.listitem.view.*

class Gitadapter(val list : List<StatewiseItem>) : RecyclerView.Adapter<Gitadapter.Adapterholder>() {
    class Adapterholder(itemView : View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Gitadapter.Adapterholder {
  val  itemView = LayoutInflater.from(parent.context).inflate(
      R.layout.listitem , parent , false
  )
           return Adapterholder(itemView)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: Gitadapter.Adapterholder, position: Int) {

        holder.itemView.states.text = list[position].state
        holder.itemView.rvconfirm.text= list[position].confirmed
        holder.itemView.rvrecover.text= list[position].recovered
        holder.itemView.rvactive.text= list[position].active
        holder.itemView.rvdeaths.text= list[position].deaths
        holder.itemView.rvdeltaconfirm.text = "↑${list[position].deltaconfirmed}"
        holder.itemView.rvdeltarecover.text = "↑${list[position].deltarecovered}"
        holder.itemView.rvdeltadeath.text = "↑${list[position].deltadeaths}"

    }
}