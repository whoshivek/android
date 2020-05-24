package com.shivek.todoproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.recylclerv.view.*
import kotlinx.android.synthetic.main.recylclerv.view.t1
import java.util.zip.Inflater

class radapter(val list: List<user>) : RecyclerView.Adapter<radapter.rholder>()
{
    class rholder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): radapter.rholder {

            return rholder(LayoutInflater.from(parent.context).inflate(R.layout.recylclerv, parent,false))

    }

    override fun getItemCount(): Int {
                return list.size
    }

    override fun onBindViewHolder(holder: radapter.rholder, position: Int) {

         holder.itemView.t1.text = list[position].task

    }

}