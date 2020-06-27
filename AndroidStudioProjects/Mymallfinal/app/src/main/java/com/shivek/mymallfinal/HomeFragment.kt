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
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.adapterandmodels.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.bannerslider

class HomeFragment : Fragment() {
    val list = arrayListOf<categorymodel>()
    val vplist = arrayListOf<modelviewpager>()
    val dlist = arrayListOf<dealofthedaymodel>()
    val gadapter = gridadapter(dlist)
    val dadapter = dealsadapter(dlist , {dealofthedaymodel ->cliicked(dealofthedaymodel)  })

    private fun cliicked(dealofthedaymodel: dealofthedaymodel) {
              startActivity(Intent(activity , productdetails::class.java))
    }

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
        list.add(categorymodel(link = null , text = "home"))
        FirebaseFirestore.getInstance().collection("category").orderBy("index").get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                         for (d in it.result!!){
                             list.add(categorymodel(link = d.get("image") as String? ,
                                text =  d.get("title") as String?
                             ))
                         }
                    categoryadapter.notifyDataSetChanged()
                }
                else{
                    val e = it.exception?.message
                    Toast.makeText(activity,e ,Toast.LENGTH_SHORT).show()

                }
            }

               vplist.add(
                   modelviewpager(
                       banner = R.drawable.ic_launcher_background
                   )
               )
        vplist.add(modelviewpager(banner = R.drawable.ic_launcher_background))
        vplist.add(modelviewpager(banner = R.drawable.home))
        vplist.add(modelviewpager(banner = R.drawable.cart))
        vplist.add(modelviewpager(banner = R.drawable.ic_launcher_background))

        dlist.add(
            dealofthedaymodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            dealofthedaymodel(
                text1 = "hi2",
                text2 = "bye2",
                text3 = "gg2"
            )
        )
        dlist.add(
            dealofthedaymodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            dealofthedaymodel(
                text1 = "hi5",
                text2 = "bye5",
                text3 = "gg5"
            )
        )
        dlist.add(
            dealofthedaymodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            dealofthedaymodel(
                text1 = "hi2",
                text2 = "bye2",
                text3 = "gg2"
            )
        )
        dlist.add(
            dealofthedaymodel(
                text1 = "hi",
                text2 = "bye",
                text3 = "gg"
            )
        )
        dlist.add(
            dealofthedaymodel(
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



        return view
    }


}