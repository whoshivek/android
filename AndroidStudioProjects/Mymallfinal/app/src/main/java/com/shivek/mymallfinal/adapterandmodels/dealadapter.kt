package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.rvmaterial.view.*

class dealadapter(val list : List<commonmodel>) : RecyclerView.Adapter<dealadapter.dholder>()
{
    class dholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(commonmodel: commonmodel) {
                      with(itemView)
                      {
                          commonmodel.image?.let { dothdayimage.setImageResource(it) }
                          dealtext1.text = commonmodel.text1
                          dealtext2.text = commonmodel.text2
                          dealtext3.text = commonmodel.text3
                      }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dholder {
        return dholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvmaterial,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: dholder, position: Int) {
        holder.bind(list[position])
    }


}