package com.example.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.listfruit.view.*

class fruitadapter (
    val fruitss : ArrayList<fruits>
): RecyclerView.Adapter<fruitadapter.viewfruitholder>()
{
    class viewfruitholder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewfruitholder {

       val itemView= LayoutInflater.from(parent.context).inflate(
            R.layout.listfruit , parent ,false
        )
        return viewfruitholder(itemView)
    }

    override fun getItemCount(): Int {
          return  fruitss.size
    }

    override fun onBindViewHolder(holder: viewfruitholder, position: Int) {
       holder.itemView.textView2.text = fruitss[position].name
        holder.itemView.textView.text = fruitss[position].origin
        holder.itemView.textView3.text = fruitss[position].quantity.toString()


    }
}