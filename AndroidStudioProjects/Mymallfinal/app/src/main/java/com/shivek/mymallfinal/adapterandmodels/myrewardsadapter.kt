package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.myrewardsrv.view.*

class myrewardsadapter (val list : List<myrewardsmodel>) : RecyclerView.Adapter<myrewardsadapter.myrewardsholder>(){
    class myrewardsholder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        fun bind(myrewardsmodel: myrewardsmodel) {
               with(itemView){
                   rewardsdescription.text = myrewardsmodel.rewardsdetails
                   rewardstitle.text = myrewardsmodel.rewardstitle
                   rewardsvalidity.text = myrewardsmodel.rewardsvalid
               }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myrewardsholder {
        return myrewardsholder(LayoutInflater.from(parent.context).inflate(R.layout.myrewardsrv , parent,false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: myrewardsholder, position: Int) {
    holder.bind(list[position])
    }

}