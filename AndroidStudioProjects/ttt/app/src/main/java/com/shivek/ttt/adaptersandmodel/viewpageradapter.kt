package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivek.ttt.R
import kotlinx.android.synthetic.main.listrecycler.view.*

import kotlinx.android.synthetic.main.viewpagerrv.view.*

class viewpageradapter(val list : List<viewpagermodel>,val click : (viewpagermodel)->Unit , val clickkk : (viewpagermodel)->Unit): RecyclerView.Adapter<viewpageradapter.viewholder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.viewpagerrv,
                parent,
                false
            )
        )
    }
private val limit = 4
    override fun getItemCount(): Int {
        if(list.size > limit){
            return limit;
        }
        else
        {
            return list.size;
        }
    }


    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(list[position] , click,clickkk)
    }
    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            modelviewpager: viewpagermodel,
            click: (viewpagermodel) -> Unit,
            clickkk: (viewpagermodel) -> Unit
        ) {
            with(itemView)
            {
                bb.setText(modelviewpager.b1)
                bbb.setText(modelviewpager.b2)
              vname.text = modelviewpager.vname
                vdate.text = modelviewpager.vdate
               Glide.with(this).load(modelviewpager.banner).into(imageviewpager)
                bb.setOnClickListener {
                    click(modelviewpager)
                }
                bbb.setOnClickListener {
                    clickkk(modelviewpager)
                }

            }
        }

    }

}


