package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.shivek.ttt.R

import kotlinx.android.synthetic.main.rv_category.view.*

class categoryadapter(val list : List<categorymodel>, val clicklistener: (categorymodel)->Unit ) : RecyclerView.Adapter<categoryadapter.AdapterHolder>()
{


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterHolder {
    return AdapterHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_category, parent, false)
    )
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {

        holder.bind(list[position] , clicklistener)

    }

    class AdapterHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(categorymodel: categorymodel, clicklistener: (categorymodel) -> Unit) {
               with(itemView)
               {
                   val circularProgressDrawable = CircularProgressDrawable(context)
                   circularProgressDrawable.strokeWidth = 5f
                   circularProgressDrawable.centerRadius = 25f
                   circularProgressDrawable.start()

                   Glide.with(this).load(categorymodel.images).placeholder(circularProgressDrawable).into(categoryimage)
                   categorytext.text = categorymodel.text
                   this.setOnClickListener { clicklistener(categorymodel) }

               }
        }

    }

    }
