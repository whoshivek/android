package com.shivek.mymallfinal.adapterandmodels

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shivek.mymallfinal.HomeFragment

class productsdetails_vp(val fm : FragmentManager ,val tab : Int ) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return  when(position){
            0 ->{
                HomeFragment()
            }
            1-> {
                HomeFragment()
            }
            else ->{
                HomeFragment()
            }
        }
    }

    override fun getCount(): Int {
    return tab
    }


}