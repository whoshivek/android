package com.shivek.mymallfinal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.adapterandmodels.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.rvmaterial.*
import kotlinx.android.synthetic.main.rvmaterial.view.*


class HomeFragment : Fragment() {
    val list = arrayListOf<categorymodel>()
    val vplist = arrayListOf<viewpagermodel>()
    val dlist = arrayListOf<commonmodel>()
    val gridlist = arrayListOf<commonmodel>()
    val testlist = arrayListOf<homepagemodel>()
    val adlist = arrayListOf<viewpagermodel>()
    val testadapter = homeadapter(testlist)
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
////////////////////////////////////////////////////////////////////////////////////////////////

       // val GRID_LAYOUT = 0
        //val DEAL_Layout = 1
        //val VIEWPAGER_LAYOUT = 2
        //val ADBANNERPAGER_LAYOUT = 3


        FirebaseFirestore.getInstance().collection("home").orderBy("index").get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    for (d in it.result!!)
                    {
                        testadapter.notifyDataSetChanged()

                        val c =   d.get("view") as Long
                        if (c == 0L)
                        {
                                           for (i in 1L..4L)
                                           {
                                               gridlist.add(commonmodel(null,null,null,image = d.get("timage" + i).toString()))
                                           }
                            testlist.add(homepagemodel(0, d.get("title").toString(), gridlist))
                            testadapter.notifyDataSetChanged()
                        }
                        else if (c == 1L)
                        {
                             val no = d.get("deal_no") as Long
                            for (i in 1L..no)
                            {
                                dlist.add(commonmodel(image = d.get("dimage"+i).toString()))
                            }
                            testlist.add(homepagemodel(1, d.get("dtitle").toString(),dlist))
                            testadapter.notifyDataSetChanged()
                        }
                        else if (c== 2L)
                        {
                            val no = d.get("banner_no") as Long
                            for (i in 1L..no)
                            {
                                vplist.add(viewpagermodel( banner = d.get("banner" +i).toString()))

                            }
                            testlist.add(homepagemodel(2 , vplist))
                            testadapter.notifyDataSetChanged()
                        }
                        else if (c == 3L)
                        {
                               adlist.add(viewpagermodel(banner = d.get("adimage").toString()))
                            testlist.add(homepagemodel(3,adlist))
                            testadapter.notifyDataSetChanged()
                        }

                    }
                }
                else
                {
                    val e = it.exception?.message
                    Toast.makeText(activity,e ,Toast.LENGTH_SHORT).show()
                }

            }


        view.category.layoutManager = LinearLayoutManager(activity , OrientationHelper.HORIZONTAL , false)
        view.category.adapter = categoryadapter




        view.test.layoutManager = LinearLayoutManager(activity)
        view.test.adapter = testadapter

        testadapter.notifyDataSetChanged()

        categoryadapter.notifyDataSetChanged()



        return view
    }


}