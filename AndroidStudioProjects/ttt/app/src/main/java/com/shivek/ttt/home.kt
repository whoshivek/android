package com.shivek.ttt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.adapterandmodels.categoryadapter
import com.shivek.mymallfinal.adapterandmodels.categorymodel
import com.shivek.mymallfinal.adapterandmodels.viewpageradapter
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import com.shivek.ttt.adaptersandmodel.listrecycle
import io.opencensus.trace.Link
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.listrv.view.*
import kotlinx.android.synthetic.main.rv_category.*
import kotlinx.android.synthetic.main.viewpager.*
import kotlinx.android.synthetic.main.viewpager.view.*


class home : Fragment() {


val categorylist = arrayListOf<categorymodel>()
    val categoryadapter = categoryadapter(categorylist , {categorymodel -> click(categorymodel) })

    private fun click(categorymodel: categorymodel) {
           //   Toast.makeText(activity , "under_construction" , Toast.LENGTH_SHORT).show()
                            val s = categorymodel.text
        startActivity(Intent(activity , category::class.java).putExtra("hello",s))
    }
    val viewlist = arrayListOf<viewpagermodel>()
    val viewAdapter =  viewpageradapter(viewlist)

    val lastlist = arrayListOf<viewpagermodel>()
    val lastadapter = listrecycle(lastlist , {viewpagermodel -> clickk(viewpagermodel
    ) })

    private fun clickk(viewpagermodel: viewpagermodel) {


              startActivity(Intent(activity , chapter::class.java))
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        FirebaseFirestore.getInstance().collection("category").orderBy("index").get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    for (d in it.result!!){
                        categorylist.add(categorymodel(link = d.get("cimage") as String? ,
                            text =  d.get("name") as String?
                        ))
                        categoryadapter.notifyDataSetChanged()

                    }
                    categoryadapter.notifyDataSetChanged()

                }
                else{
                    val e = it.exception?.message
                    Toast.makeText(activity,e ,Toast.LENGTH_SHORT).show()

                }

            }

        FirebaseFirestore.getInstance().collection("viewpagerrr").orderBy("index").get()
            .addOnCompleteListener {
            if (it.isSuccessful)
            {

                for (d in it.result!!){

                    viewlist.add(viewpagermodel(banner = d.get("vimage") as String , vdate = d.get("vdate") as String,
                    vname = d.get("vname") as String , b1 = d.get("vbutton1") as String , b2= d.get("vbutton2") as String
                        ))
                    viewAdapter.notifyDataSetChanged()
                }
                viewAdapter.notifyDataSetChanged()
            }
                else{
                val e = it.exception?.message
                Toast.makeText(activity , e , Toast.LENGTH_SHORT).show()
            }
            }


        FirebaseFirestore.getInstance().collection("lastadapter").orderBy("index").get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {

                    for (d in it.result!!) {
                        lastlist.add(viewpagermodel(banner = d.get("limage") as String , vname = d.get("ltitle") as String
                        ,b1 =d.get("lb1") as String,b2 =d.get("lb2") as String,b1date =d.get("lb1d1") as String,
                            b2date =d.get("lb2d2") as String

                        ))

                        lastadapter.notifyDataSetChanged()
                    }

                    lastadapter.notifyDataSetChanged()
                }
                else{
                    val e = it.exception?.message.toString()
                    Toast.makeText(activity , e , Toast.LENGTH_SHORT).show()
                }
            }



        v.listrv.layoutManager =LinearLayoutManager(activity)
        v.listrv.adapter = lastadapter
        lastadapter.notifyDataSetChanged()




v.category.layoutManager = LinearLayoutManager(activity , OrientationHelper.HORIZONTAL , false)
        v.category.adapter = categoryadapter
        categoryadapter.notifyDataSetChanged()


     //   viewlist.add(viewpagermodel(banner = "https://i2.wp.com/twilightscans.com/wp-content/uploads/2020/06/50117.jpg?resize=125%2C180&ssl=1"))
       // viewlist.add(viewpagermodel(banner = "https://i2.wp.com/twilightscans.com/wp-content/uploads/2020/06/50117.jpg?resize=125%2C180&ssl=1"))
        //viewlist.add(viewpagermodel(banner = "https://i2.wp.com/twilightscans.com/wp-content/uploads/2020/06/50117.jpg?resize=125%2C180&ssl=1"))

        v.viewpagerrv.adapter = viewAdapter
        viewAdapter.notifyDataSetChanged()
        v.indicator.setViewPager(v.viewpagerrv)
        viewAdapter.registerAdapterDataObserver(v.indicator.adapterDataObserver)
       v.viewpagerrv.offscreenPageLimit = 5
        return  v

    }


}