package com.shivek.mymallfinal.adapterandmodels

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.activity_categoryactivity.view.*
import kotlinx.android.synthetic.main.dealoftheday_rv.view.*
import kotlinx.android.synthetic.main.vp_bannerslider.view.*

class testingadapter(val context : Context, val list: List<testingmodel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    internal val VIEW_TYPE_ONE = 1
    internal val VIEW_TYPE_TWO = 2
    internal val VIEW_TYPE_TWHREE = 3


    class ViewHolder1 (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val adapterandmodels: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

        @SuppressLint("WrongConstant")
        fun bind(position: testingmodel) {
            with(itemView)
            {
                val list = arrayListOf<modelviewpager>()
                val dadapter = com.shivek.mymallfinal.adapterandmodels.viewpageradapter(list)
                dealsoftheday2.layoutManager = LinearLayoutManager(context , OrientationHelper.HORIZONTAL ,false)
                dealsoftheday2.adapter = dadapter


            }
        }


    }

    class ViewHolder2 (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind2(position: testingmodel) {
            with(itemView)
            {
                position.image?.let { dothdayimage.setImageResource(it) }
                dealtext1.text =position.text1
                dealtext2.text = position.text2
                dealtext3.text = position.text3
            }
        }


    }

    class ViewHolder3 (itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind3(mycartmodel: testingmodel) {
    with(itemView)
    {

        mycartmodel.banner?.let { banner_slide.setImageResource(it) }
    }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ONE) {
            ViewHolder1(LayoutInflater.from(context).inflate(R.layout.dealoftheday_rv, parent, false))
        } else if (viewType == VIEW_TYPE_TWO){
            ViewHolder2(LayoutInflater.from(context).inflate(R.layout.dealoftheday_rv,parent,false))
        }
        else if (viewType == VIEW_TYPE_TWHREE) {
            ViewHolder3(LayoutInflater.from(context).inflate(R.layout.vp_bannerslider, parent, false))
        }
        else
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.vp_bannerslider, parent, false))
        }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        // here you can get decide from your model's ArrayList, which type of view you need to load. Like
         if (list[position].type == 0) { // put your condition, according to your requirements
        return    VIEW_TYPE_ONE
        } else if (list[position].type==1)
        {
            return VIEW_TYPE_TWO
        }
        else{
             return VIEW_TYPE_TWHREE
         }
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onBindViewHolder(
        holder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
        position: Int
    ) {
        if (list[position].type == 0) { // put your condition, according to your requirements
            (holder as ViewHolder1).bind(list[position])
        } else if (list[position].type==1){
            (holder as ViewHolder2).bind2(list[position])
        }else
            (holder as ViewHolder3).bind3(list[position])
    }

}


