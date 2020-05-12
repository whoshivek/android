package com.example.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class viewpager(fm : FragmentManager) : FragmentStatePagerAdapter(fm)
{
    val list = arrayListOf<Fragment>()
    fun add(fragment: Fragment)
    {
        list.add(fragment)
    }
    override fun getItem(position: Int): Fragment = list[position]


    override fun getCount()= list.size

}