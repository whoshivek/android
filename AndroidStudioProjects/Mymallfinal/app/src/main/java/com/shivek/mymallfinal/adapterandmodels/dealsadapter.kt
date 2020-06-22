package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.dealoftheday_rv.view.*

class dealsadapter(val list : List<dealofthedaymodel> , val clicklstener : (dealofthedaymodel)->Unit) : RecyclerView.Adapter<dealsadapter.dholder>()
{
    class dholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dealofthedaymodel: dealofthedaymodel , clicklistener: (dealofthedaymodel) -> Unit) {
                      with(itemView)
                      {
                          dealofthedaymodel.image?.let { dothdayimage.setImageResource(it) }
                          dealtext1.text = dealofthedaymodel.text1
                          dealtext2.text = dealofthedaymodel.text2
                          dealtext3.text = dealofthedaymodel.text3
                          this.setOnClickListener { clicklistener(dealofthedaymodel) }
                      }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dholder {
        return dholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dealoftheday_rv,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: dholder, position: Int) {
        holder.bind(list[position] , clicklstener)
    }


}