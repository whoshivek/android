package com.example.kotlinretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview.view.*
import java.util.zip.Inflater

class adapterapi(val gitusers: ArrayList<Gitusers>) : RecyclerView.Adapter<adapterapi.userviewholder>()
{
    class userviewholder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userviewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(
          R.layout.recyclerview , parent , false
        )
        return userviewholder(itemView)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")

        return gitusers.size
    }

    override fun onBindViewHolder(holder: userviewholder, position: Int) {
        TODO("Not yet implemented")
        holder.itemView.t1.text = gitusers[position].login
        holder.itemView.t2.text = gitusers[position].followingUrl
        Picasso.get().load(gitusers[position].avatarUrl).into(holder.itemView.i);

    }

}


