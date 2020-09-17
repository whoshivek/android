package com.manish.pdfreaderrr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.manish.pdfreaderrr.R
import kotlinx.android.synthetic.main.fragment_bookname.view.*


class bookname : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_bookname, container, false)

        val c = activity?.intent?.getStringExtra("bookname")



        if (c != null) {
            FirebaseFirestore.getInstance().collection("lastadapter").document(c).get()
                .addOnCompleteListener {
                    if (it.isSuccessful)
                    {


                            v.description.text = it.result?.get("comicdescription") as String
                        v.comicname.text = it.result?.get("comicname") as String
                        v.genre.text = it.result?.get("genre") as String

                    }
                }
        }
        return v
    }


}