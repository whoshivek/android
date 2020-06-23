package com.shivek.mymallfinal.adapterandmodels

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shivek.mymallfinal.HomeFragment
import com.shivek.mymallfinal.productdescription
import com.shivek.mymallfinal.productspecification

class productsdetails_vp(val fm : FragmentManager ,val tab : Int ) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return  when(position){
            0 -> {
                  productdescription()
            }
            1-> {
                productspecification()
            }
            else ->{
                productdescription()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
        0 -> "description"
        1-> "Specification"
        else -> "other details"
        }
    }
    override fun getCount(): Int {
    return tab
    }


}