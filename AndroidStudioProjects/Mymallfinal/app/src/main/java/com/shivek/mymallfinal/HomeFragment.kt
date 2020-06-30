package com.shivek.mymallfinal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.adapterandmodels.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    val list = arrayListOf<categorymodel>()
    val vplist = arrayListOf<viewpagermodel>()
    val dlist = arrayListOf<commonmodel>()
    val gadapter = gridadapter(dlist)
    val dadapter = dealadapter(dlist )
    val adlist = arrayListOf<viewpagermodel>()

    val vpadapter =
        viewpageradapter(vplist)
    val categoryadapter =
        com.shivek.mymallfinal.adapterandmodels.categoryadapter(
            list,
            { categorymodel -> fnitemclicked(categorymodel) })

    private fun fnitemclicked(categorymodel: categorymodel) {
            val s = categorymodel.text
        startActivity(Intent(activity , categoryactivity::class.java).putExtra("cat",s))

    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
val view = inflater.inflate(R.layout.fragment_home, container, false)

        FirebaseFirestore.getInstance().collection("onead").document("ad").get()
           .addOnCompleteListener {
               if (it.isSuccessful)
               {
                  Glide.with(this).load(it.result?.get("image")).into(adsingle)

               }
               else{
                   val e = it.exception?.message
                   Toast.makeText(activity,e ,Toast.LENGTH_SHORT).show()

               }
           }
        FirebaseFirestore.getInstance().collection("category").orderBy("index").get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                         for (d in it.result!!){
                             list.add(categorymodel(link = d.get("image") as String? ,
                                text =  d.get("title") as String?
                             ))
                             categoryadapter.notifyDataSetChanged()

                         }

                }
                else{
                    val e = it.exception?.message
                    Toast.makeText(activity,e ,Toast.LENGTH_SHORT).show()

                }

            }

               vplist.add(
                   viewpagermodel(
                       banner = R.drawable.ic_launcher_background
                   )
               )
        vplist.add(viewpagermodel(banner = R.drawable.ic_launcher_background))
        vplist.add(viewpagermodel(banner = R.drawable.home))
        vplist.add(viewpagermodel(banner = R.drawable.home))
        vplist.add(viewpagermodel(banner = R.drawable.ic_launcher_background))

        dlist.add(
            commonmodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi2",
                text2 = "bye2",
                text3 = "gg2"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi5",
                text2 = "bye5",
                text3 = "gg5"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi2",
                text2 = "bye2",
                text3 = "gg2"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            commonmodel(
                text1 = "hi5",
                text2 = "bye5",
                text3 = "gg5"
            )
        )

        view.gridlayout.adapter = gadapter
        gadapter.notifyDataSetChanged()
        view.dealsoftheday.layoutManager = LinearLayoutManager(activity , OrientationHelper.HORIZONTAL ,false)
        view.dealsoftheday.adapter = dadapter
        dadapter.notifyDataSetChanged()
        view.bannerslider.adapter = vpadapter
        view.bannerslider.beginFakeDrag()
        vpadapter.notifyDataSetChanged()
        view.category.layoutManager = LinearLayoutManager(activity , OrientationHelper.HORIZONTAL , false)
        view.category.adapter = categoryadapter
        categoryadapter.notifyDataSetChanged()
          adlist.add(viewpagermodel(R.drawable.home))
////////////////////////////////////////////////////////////////////////////////////////////////

       // val GRID_LAYOUT = 0
        //val DEAL_Layout = 1
        //val VIEWPAGER_LAYOUT = 2
        //val ADBANNERPAGER_LAYOUT = 3


        val testlist = arrayListOf<homepagemodel>()
        testlist.add(homepagemodel(2, vplist))
        testlist.add(homepagemodel(1,"deal of the year",dlist))
        testlist.add(homepagemodel(0,"#trending",dlist))
        testlist.add(homepagemodel(3, adlist))

        val testadapter = homeadapter(testlist)

        view.test.layoutManager = LinearLayoutManager(activity)
        view.test.adapter = testadapter
        testadapter.notifyDataSetChanged()







        return view
    }


}