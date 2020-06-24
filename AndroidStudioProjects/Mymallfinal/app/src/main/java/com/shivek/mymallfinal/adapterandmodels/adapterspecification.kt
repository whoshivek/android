package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.fragment_productspecification.view.*
import kotlinx.android.synthetic.main.rv_specs.view.*
import kotlinx.android.synthetic.main.specsrvheading.view.*

class adapterspecification (val list : List<modelspecification>) : RecyclerView.Adapter<adapterspecification.sholder>()
{

    private val viewPool = RecyclerView.RecycledViewPool()

    class sholder (itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(modelspecification: modelspecification) {
              with(itemView){
                 tvvvv.text = modelspecification.heading
              }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterspecification.sholder {
        return sholder(LayoutInflater.from(parent.context).inflate(R.layout.specsrvheading, parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: adapterspecification.sholder, position: Int) {
        holder.bind(list[position])

        val childla =  LinearLayoutManager(holder.itemView.subrecycler.context , LinearLayoutManager.VERTICAL ,false)
        holder.itemView.subrecycler.apply {
            layoutManager = childla
            adapter = subadapter(list.get(position))
            setRecycledViewPool(viewPool)
        }
    }


}