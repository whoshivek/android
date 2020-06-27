package com.shivek.mymallfinal.adapterandmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivek.mymallfinal.R
import kotlinx.android.synthetic.main.myorderrv.view.*

class myorderadapter(val list : List<myordermodel> , val onclick :(myordermodel)->Unit ) : RecyclerView.Adapter<myorderadapter.myorder>(){
    class myorder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            myordermodel: myordermodel,
            onclick: (myordermodel) -> Unit
        ) {
            with(itemView){
                myordername.text = myordermodel.name
                imagemyorder.setImageResource(myordermodel.image)
itemView.setOnClickListener {
    onclick(myordermodel)
}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myorderadapter.myorder {
      return myorder(LayoutInflater.from(parent.context).inflate(R.layout.myorderrv, parent,false))
    }

    override fun getItemCount(): Int {
            return list.size
    }

    override fun onBindViewHolder(holder: myorderadapter.myorder, position: Int) {
        holder.bind(list[position] , onclick)


    }


}