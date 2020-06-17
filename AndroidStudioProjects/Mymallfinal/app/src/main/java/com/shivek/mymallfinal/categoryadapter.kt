package com.shivek.mymallfinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_category.view.*

class categoryadapter(val list : List<categorymodel>) : RecyclerView.Adapter<categoryadapter.AdapterHolder>()
{


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): categoryadapter.AdapterHolder {
    return AdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_category , parent , false))
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: categoryadapter.AdapterHolder, position: Int) {

        holder.bind(list[position])
    }

    class AdapterHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categorymodel: categorymodel) {
            with(itemView)
            {
                categorytext.text = categorymodel.text



            }

        }

    }
}