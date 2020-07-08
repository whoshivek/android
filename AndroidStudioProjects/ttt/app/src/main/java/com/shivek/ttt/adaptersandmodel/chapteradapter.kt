package com.shivek.ttt.adaptersandmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.storage.FirebaseStorage
import com.shivek.mymallfinal.adapterandmodels.categorymodel
import com.shivek.ttt.R
import kotlinx.android.synthetic.main.chapterrv.*
import kotlinx.android.synthetic.main.chapterrv.view.*
import kotlinx.android.synthetic.main.rv_category.view.*

class chapteradapter(val list : List<categorymodel>) : RecyclerView.Adapter<chapteradapter.AdapterHolder>()
{


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterHolder {
        return AdapterHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.chapterrv, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {

        holder.bind(list[position] )

    }

    class AdapterHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(categorymodel: categorymodel) {
            with(itemView)
            {
                val circularProgressDrawable = CircularProgressDrawable(context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 45f
                circularProgressDrawable.start()
                val f = categorymodel.link?.let {
                    FirebaseStorage.getInstance().getReferenceFromUrl(
                        it
                    )
                }
                GlideApp.with(this).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).override(1600 ,30000).placeholder(circularProgressDrawable).load(f).into(container)



            }
        }

    }

}
