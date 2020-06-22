package com.shivek.a7821048896project.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.a7821048896project.R
import com.shivek.a7821048896project.ischecked
import com.shivek.a7821048896project.roomdb.modelclass
import kotlinx.android.synthetic.main.recyclerviewdesign.view.*

class rvadapter(val list : List<modelclass> ,
                val onclick : (modelclass)->Unit
)
: RecyclerView.Adapter<rvadapter.rvholder>()

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvadapter.rvholder {
      return rvholder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewdesign , parent , false))
    }

    override fun getItemCount(): Int {
return list.size
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }
    override fun onBindViewHolder(holder: rvadapter.rvholder, position: Int) {
      holder.bind(list[position] , onclick )


    }


    class rvholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            modelclass: modelclass,
            onclick: (modelclass) -> Unit
        ) {
            with(itemView)
            {
                var v = false
                tv1.text = modelclass.name
                tv2.text = modelclass.contact
                modelclass.image?.let { img.setImageResource(it) }
                 

            }
        }

       }

    }

