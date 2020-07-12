package com.shivek.ttt.adaptersandmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shivek.ttt.bookname
import com.shivek.ttt.contactus
import com.shivek.ttt.home

class productviewpager_vp(val fm : FragmentManager, val tab : Int ) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return  when(position){
            0 -> {
                bookname()
            }

            else ->{
                chapterlist()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "description"
            else -> "Chapters"
        }
    }
    override fun getCount(): Int {
        return tab
    }


}