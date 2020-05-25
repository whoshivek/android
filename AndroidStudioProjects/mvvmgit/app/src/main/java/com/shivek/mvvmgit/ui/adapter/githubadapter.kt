package com.shivek.mvvmgit.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mvvmgit.R
import com.shivek.mvvmgit.data.models.Allgituser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recy.view.*

class githubadapter (val list : List<Allgituser>) : RecyclerView.Adapter<githubadapter.rholder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rholder {
        return rholder(LayoutInflater.from(parent.context).inflate(R.layout.recy , parent , false))

    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: rholder, position: Int) {
    holder.itemView.tvv.text = list[position].login
        Picasso.get().load(list[position].avatarUrl).into(holder.itemView.image)


    }

    class rholder(itemView: View): RecyclerView.ViewHolder(itemView) {



        }

    }

