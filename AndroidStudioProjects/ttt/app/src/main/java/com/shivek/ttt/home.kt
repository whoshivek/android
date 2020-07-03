package com.shivek.ttt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.adapterandmodels.categoryadapter
import com.shivek.mymallfinal.adapterandmodels.categorymodel


class home : Fragment() {


val categorylist = arrayListOf<categorymodel>()
    val adapter = categoryadapter(categorylist , {categorymodel -> click(categorymodel) })

    private fun click(categorymodel: categorymodel) {
              Toast.makeText(activity , "under_construction" , Toast.LENGTH_SHORT).show()
    }

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
                   for (i in it.result )
               }
           }



return  v

    }


}