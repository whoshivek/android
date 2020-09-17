package com.shivek.paigham

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class walkscreenviewpager(val list : List<screenitems> , val context : Context) : PagerAdapter() {
    override fun getCount(): Int {
       return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {

    }

}