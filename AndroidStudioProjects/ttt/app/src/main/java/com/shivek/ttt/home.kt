package com.shivek.ttt

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.shivek.mymallfinal.adapterandmodels.categoryadapter
import com.shivek.mymallfinal.adapterandmodels.categorymodel
import com.shivek.mymallfinal.adapterandmodels.viewpageradapter
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import com.shivek.ttt.adaptersandmodel.gridadapter
import com.shivek.ttt.adaptersandmodel.listrecycle
import com.shivek.ttt.adaptersandmodel.productviewpager_vp
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
    val viewAdapter =  viewpageradapter(viewlist,{viewpagermodel -> vclickk(viewpagermodel) } , {viewpagermodel -> vclickkk(viewpagermodel)})

    private fun vclickkk(viewpagermodel: viewpagermodel) {
        val k = viewpagermodel.vname
        val j = viewpagermodel.b2
        startActivity(Intent(activity , chapter::class.java).putExtra("hii",k).putExtra("hi",j).putExtra("image",viewpagermodel.banner))
    }


    private fun vclickk(viewpagermodel: viewpagermodel) {
        val k = viewpagermodel.vname
        val j = viewpagermodel.b1
        startActivity(Intent(activity , chapter::class.java).putExtra("hii",k).putExtra("hi",j).putExtra("image",viewpagermodel.banner))
    }

    val lastlist = arrayListOf<viewpagermodel>()
    val lastadapter = gridadapter(lastlist , { viewpagermodel -> clickk(viewpagermodel
    ) } , {viewpagermodel -> clickkk(viewpagermodel)  },{viewpagermodel -> sclick(viewpagermodel) })

    private fun sclick(viewpagermodel: viewpagermodel) {
             startActivity(Intent(activity,boookkk::class.java).putExtra("bookname",viewpagermodel.vname))
    }

    private fun clickkk(viewpagermodel: viewpagermodel) {
        val k = viewpagermodel.vname
        val j = viewpagermodel.b2
        startActivity(Intent(activity , chapter::class.java).putExtra("hii",k).putExtra("hi",j).putExtra("image",viewpagermodel.banner))
    }

    private fun clickk(viewpagermodel: viewpagermodel) {

                        val k = viewpagermodel.vname
        val j = viewpagermodel.b1
              startActivity(Intent(activity , chapter::class.java).putExtra("hii",k).putExtra("hi",j).putExtra("image",viewpagermodel.banner))
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)

       categorylist.add(categorymodel(text = "Romance" , images = R.drawable.romance))
        categorylist.add(categorymodel(text = "Comedy" , images =R.drawable.comedy ))
        categorylist.add(categorymodel(text = "Shoujo", images = R.drawable.source))
        categorylist.add(categorymodel(text = "Drama",images = R.drawable.drama))
       categorylist.add(categorymodel(text = "School life",images = R.drawable.schoollife))
        categorylist.add(categorymodel(text = "Shounen",images = R.drawable.giphy))
        categorylist.add(categorymodel(text = "Action",images = R.drawable.action))

        categoryadapter.notifyDataSetChanged()



        FirebaseFirestore.getInstance().collection("lastadapter").orderBy("index").get()
            .addOnCompleteListener {
            if (it.isSuccessful)
            {

                for (d in it.result!!){

                    viewlist.add(viewpagermodel(banner = d.get("comicimage") as String , vdate ="today date" as String,
                    vname = d.get("comicname") as String , b1 =  "chapter-${d.get("noofchapters") as Long}" as String ,
                        b2= "chapter-${d.get("noofchapters") as Long-1}" as String
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
                        lastlist.add(viewpagermodel( banner = d.get("comicimage") as String,vname = d.get("comicname") as String
                        ,b1 ="chapter-${d.get("noofchapters") as Long}" as String,b2 ="chapter-${d.get("noofchapters") as Long -1}" as String

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



        v.listrv.layoutManager =GridLayoutManager(activity,2)
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





        v.discord.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://discord.com/invite/eH4y7eZ")
            startActivity(i)
        }

        v.paypal.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.paypal.me/MANGANIC007")
            startActivity(i)
        }

        v.patreon.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.patreon.com/twilightscanlations")
            startActivity(i)
        }








        return  v

    }


}