package com.shivek.a7821048896project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivek.a7821048896project.recycleradapter.rvadapter
import com.shivek.a7821048896project.roomdb.database
import com.shivek.a7821048896project.roomdb.modelclass
import kotlinx.android.synthetic.main.activity_showdetails1.*
import kotlinx.android.synthetic.main.recyclerviewdesign.*
var ischecked : Boolean = false
class showdetails1 : AppCompatActivity() {

    val list = arrayListOf<modelclass>()
    val adapter = rvadapter(list , {modelclass -> onclickk(modelclass) })




    private fun onclickk(modelclass: modelclass) {


            modelclass.image = R.drawable.check
            modelclass.ischecked = 1





        }





    val db by lazy {
    database.getDatabase(this)
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showdetails1)

db.TODOdao().task().observe(this , Observer {
    if (!it.isNullOrEmpty())
    {
        list.clear()
        list.addAll(it)
        adapter.notifyDataSetChanged()
    }
    else{
        list.clear()
        adapter.notifyDataSetChanged()
    }
})


        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        adapter.notifyDataSetChanged()



    }



}
