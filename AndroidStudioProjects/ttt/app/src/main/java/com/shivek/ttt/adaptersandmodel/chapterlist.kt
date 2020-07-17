package com.shivek.ttt.adaptersandmodel

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import com.shivek.ttt.R
import com.shivek.ttt.chapter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_bookname.view.*
import kotlinx.android.synthetic.main.fragment_chapterlist.view.*


class chapterlist : Fragment() {

val list = arrayListOf<viewpagermodel>()
    val adapater = chaperlist(list , {viewpagermodel ->click(viewpagermodel)  })

    private fun click(viewpagermodel: viewpagermodel) {
        val c = activity?.intent?.getStringExtra("bookname")
        if (c != null) {
            FirebaseFirestore.getInstance().collection("lastadapter").document(c).get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val d = viewpagermodel.vdate
                        val e = it.result.get("comicimage") as String

                        startActivity(Intent(activity , chapter::class.java).putExtra("hii",c)
                            .putExtra("hi",d)
                            .putExtra("image",e))
                    }
                    else
                    {
                        val ss = it.exception?.message
                        if (ss != null) {
                            context?.let { it1 -> Toasty.error(it1,ss,Toasty.LENGTH_SHORT).show() }
                        }
                    }
                }
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_chapterlist, container, false)
        val c = activity?.intent?.getStringExtra("bookname")
        FirebaseFirestore.getInstance().collection("lastadapter").whereEqualTo("comicname",c).get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    adapater.notifyDataSetChanged()
                    for (d in it.result)
                    {
                        val dd = d.get("lastchapternumber?") as Long
                        val ee = d.get("chapterstartingfrom?") as Long
                        for (i in ee..dd)
                        {
                            list.add(viewpagermodel(vname = "\t• " , vdate =  "chapter-${i}"))
                        adapater.notifyDataSetChanged()
                        }
                        adapater.notifyDataSetChanged()

                    }
                }
            }

        v.chapterlistrv.layoutManager = LinearLayoutManager(activity)
        v.chapterlistrv.adapter = adapater
        adapater.notifyDataSetChanged()
        return v

    }

}