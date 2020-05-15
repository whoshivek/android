package com.example.javanetwork

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview.view.*

class gitadapter (val gituser : ArrayList<gitusers>) : RecyclerView.Adapter<gitadapter.viewgitholder>()
{
    class viewgitholder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): gitadapter.viewgitholder {
        val itemView =LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview , parent ,false
        )
        return viewgitholder(itemView)
    }

    override fun getItemCount(): Int {
        return gituser.size
    }

    override fun onBindViewHolder(holder: gitadapter.viewgitholder, position: Int) {
        holder.itemView.logint.text = gituser[position].login
        holder.itemView.idt.text = gituser[position].id.toString();
        Picasso.get().load(gituser[position].avatar_url).placeholder(android.R.drawable.alert_dark_frame).into(holder.itemView.imagess);
    }

}




