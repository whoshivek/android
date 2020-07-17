package com.shivek.ttt

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import com.shivek.ttt.adaptersandmodel.gridadapter
import kotlinx.android.synthetic.main.fragment_searchfragment.view.*

class searchfragment(newText: String) : Fragment() {
    val lastlist = arrayListOf<viewpagermodel>()
      val c = newText
    val searchlist = arrayListOf<viewpagermodel>()
    val lastadapter = gridadapter(searchlist , { viewpagermodel -> clickk(viewpagermodel
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_searchfragment, container, false)
        FirebaseFirestore.getInstance().collection("lastadapter").orderBy("Timestamp").get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {

                    for (d in it.result!!) {

                        lastlist.add(viewpagermodel(vname = d.get("comicname") as String, banner = d.get("comicimage") as String))
                        searchlist.clear()
                        searchlist.addAll(lastlist.filter { viewpagermodel ->viewpagermodel.vname?.contains(c,true)!!  })
                        lastadapter.notifyDataSetChanged()




                    }

                }
                else{
                    val e = it.exception?.message.toString()
                    Toast.makeText(activity , e , Toast.LENGTH_SHORT).show()
                }
            }





        v.searchlistrv.layoutManager = GridLayoutManager(activity,2)
        v.searchlistrv.adapter = lastadapter
        lastadapter.notifyDataSetChanged()

        return v



    }

}