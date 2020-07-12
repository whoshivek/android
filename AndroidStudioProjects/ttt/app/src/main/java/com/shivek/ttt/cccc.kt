package com.shivek.ttt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.storage.FirebaseStorage
import com.shivek.mymallfinal.adapterandmodels.categorymodel
import com.shivek.ttt.adaptersandmodel.chapteradapter
import kotlinx.android.synthetic.main.fragment_cccc.view.*


class cccc(titlee : String?) : Fragment() {
    val list = arrayListOf<categorymodel>()
    val adapter = chapteradapter(list)
    val spinner = arrayListOf<String>()
      val ti = titlee
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_cccc, container, false)
      //  val title = activity?.intent?.getStringExtra("hi")

        val chapter = activity?.intent?.getStringExtra("hii")
      //  v.chaptername.text = chapter

        FirebaseStorage.getInstance().getReference("${chapter}${ti}").listAll()
            .addOnSuccessListener {
                val e = it.items.size
                for (d in 1..e)
                {
                    list.add(categorymodel(link= "gs://twilightscans-9660c.appspot.com/${chapter}${ti}/c${d}.jpg" as String) )
                    adapter.notifyDataSetChanged()
                }

            }


        v.chapterrv.layoutManager = LinearLayoutManager(activity)
        v.chapterrv.adapter = adapter
        adapter.notifyDataSetChanged()



        return v

    }

}