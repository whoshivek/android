package com.shivek.mymallfinal.adapterandmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.mycartpricedetailsrv.view.*
import kotlinx.android.synthetic.main.mycartrv.view.*
import androidx.recyclerview.widget.RecyclerView as RecyclerView1

class mycartadapter( val context : Context , val list: List<mycartmodel>) : Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>(){

    internal val VIEW_TYPE_ONE = 1
    internal val VIEW_TYPE_TWO = 2

   class ViewHolder1 (itemView: View) : RecyclerView1.ViewHolder(itemView) {
        fun bind(position: mycartmodel) {
         with(itemView)
         {
             position.image?.let { rvimage.setImageResource(it) }
             rvcartname.text = position.name
             rvcartprice.text = position.price
         }
        }


    }

class ViewHolder2 (itemView: View) : RecyclerView1.ViewHolder(itemView) {
        fun bind2(position: mycartmodel) {
             with(itemView)
             {
                 deliverycost.text = position.delivert
                 totalamount.text = position.totalamount
             }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView1.ViewHolder {
        return if (viewType == VIEW_TYPE_ONE) {
            ViewHolder1(LayoutInflater.from(context).inflate(R.layout.mycartrv, parent, false))
        } else if (viewType == VIEW_TYPE_TWO){
            ViewHolder2(LayoutInflater.from(context).inflate(R.layout.mycartpricedetailsrv,parent,false))
        }
        else
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.mycartrv , parent,false))//if it's not VIEW_TYPE_ONE then its VIEW_TYPE_TWO
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        // here you can get decide from your model's ArrayList, which type of view you need to load. Like
        return if (list[position].type === 0) { // put your condition, according to your requirements
            VIEW_TYPE_ONE
        } else VIEW_TYPE_TWO
    }



    class ViewHolder(itemView: View): RecyclerView1.ViewHolder(itemView) {

    }

    override fun onBindViewHolder(
        holder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
        position: Int
    ) {
        if (list[position].type == 0) { // put your condition, according to your requirements
            (holder as ViewHolder1).bind(list[position])
        } else {
            (holder as ViewHolder2).bind2(list[position])
        }
    }

}