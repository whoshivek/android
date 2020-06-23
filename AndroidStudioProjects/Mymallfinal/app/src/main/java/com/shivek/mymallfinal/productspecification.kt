package com.shivek.mymallfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivek.mymallfinal.adapterandmodels.adapterspecification
import com.shivek.mymallfinal.adapterandmodels.modelspecification
import kotlinx.android.synthetic.main.fragment_productspecification.view.*


class productspecification : Fragment() {


    val list = arrayListOf<modelspecification>()
    val adapter = adapterspecification(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                    list.add(modelspecification(title = "hi" , topic = "jbje"))
        list.add(modelspecification(title = "hi" , topic = "jbje"))
        list.add(modelspecification(title = "hi" , topic = "jbje"))
        list.add(modelspecification(title = "hi" , topic = "jbje"))
        list.add(modelspecification(title = "hi" , topic = "jbje"))
        list.add(modelspecification(title = "hi" , topic = "jbje"))

        val v =  inflater.inflate(R.layout.fragment_productspecification, container, false)
             v.productspecsrv.layoutManager = LinearLayoutManager(activity )
        v.productspecsrv.adapter = adapter
        adapter.notifyDataSetChanged()
        return v
    }


}