package com.shivek.ttt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import com.shivek.ttt.adaptersandmodel.listrecycle
import kotlinx.android.synthetic.main.listrv.*
import kotlinx.android.synthetic.main.listrv.view.*

class category : AppCompatActivity() {

    val lastlist = arrayListOf<viewpagermodel>()
    val lastadapter = listrecycle(lastlist , {viewpagermodel ->click(viewpagermodel)  })

    private fun click(viewpagermodel: viewpagermodel) {

        val c = viewpagermodel.vname
        val d = viewpagermodel.b1
           startActivity(Intent(this , chapter::class.java).putExtra("hi" , d).putExtra("hii" , c))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
          val title = intent.getStringExtra("hello")
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setTitle(title)



        FirebaseFirestore.getInstance().collection(title).orderBy("index").get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {

                    for (d in it.result!!) {
                        lastlist.add(
                            viewpagermodel(banner = d.get("limage") as String , vname = d.get("ltitle") as String
                            ,b1 =d.get("lb1") as String,b2 =d.get("lb2") as String,b1date =d.get("lb1d1") as String,
                            b2date =d.get("lb2d2") as String

                        )
                        )

                        lastadapter.notifyDataSetChanged()
                    }

                    lastadapter.notifyDataSetChanged()
                }
                else{
                    val e = it.exception?.message.toString()
                    Toast.makeText(this , e , Toast.LENGTH_SHORT).show()
                }
            }


        listrv.layoutManager = LinearLayoutManager(this)
        listrv.adapter = lastadapter
        lastadapter.notifyDataSetChanged()


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home ->{
             startActivity(Intent(this , MainActivity2::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}