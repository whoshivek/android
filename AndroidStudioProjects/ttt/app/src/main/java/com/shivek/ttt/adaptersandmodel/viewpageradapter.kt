package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.shivek.ttt.R
import com.wangpeiyuan.cycleviewpager2.adapter.CyclePagerAdapter
import kotlinx.android.synthetic.main.viewpager.*

import kotlinx.android.synthetic.main.viewpagerrv.view.*

class viewpageradapter(val list : ArrayList<viewpagermodel>,val click : (viewpagermodel)->Unit , val clickkk : (viewpagermodel)->Unit
,val vclick : (viewpagermodel)->Unit ): CyclePagerAdapter<viewpageradapter.viewholder>()
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
private val limit = 6
    override fun getRealItemCount(): Int {
        if(list.size > limit){
            return limit;
        }
        else
        {
            return list.size;
        }
    }


    override fun onBindRealViewHolder(holder: viewholder, position: Int) {
        holder.bind(list[position] , click,clickkk,vclick)

    }
    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            modelviewpager: viewpagermodel,
            click: (viewpagermodel) -> Unit,
            clickkk: (viewpagermodel) -> Unit,
            vclick: (viewpagermodel) -> Unit
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
                if (bbb.text.isNullOrBlank())
                {
                    bbb.visibility = View.GONE
                }
                  imageviewpager.setOnClickListener {
                      vclick(modelviewpager)
                  }
                vname.setOnClickListener {
                    vclick(modelviewpager)
                }
            }
        }

    }



}


