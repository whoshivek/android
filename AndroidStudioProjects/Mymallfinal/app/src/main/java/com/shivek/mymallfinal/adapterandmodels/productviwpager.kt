package com.shivek.mymallfinal.adapterandmodels

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter



class productviwpager(val list : List<viewpagermodel>) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val productimage = ImageView(container.context)
        list[position].banner?.let { productimage.setImageResource(it) }
        container.addView(productimage , 0)
        return productimage
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)

    }
    override fun getCount(): Int {
      return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
  return view == `object`
    }

}




