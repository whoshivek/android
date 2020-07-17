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
    val lastadapter = listrecycle(lastlist , {viewpagermodel ->click(viewpagermodel)  },{viewpagermodel -> clickk(viewpagermodel)  }

    , {viewpagermodel ->sclick(viewpagermodel)  })

    private fun sclick(viewpagermodel: viewpagermodel) {
         startActivity(Intent(this , boookkk::class.java).putExtra("bookname",viewpagermodel.vname))
    }

    private fun clickk(viewpagermodel: viewpagermodel) {
        val c = viewpagermodel.vname
        val d = viewpagermodel.b2
        startActivity(Intent(this , chapter::class.java).putExtra("hi" , d).putExtra("hii" , c)
            .putExtra("image",viewpagermodel.banner))
    }

    private fun click(viewpagermodel: viewpagermodel) {

        val c = viewpagermodel.vname
        val d = viewpagermodel.b1
           startActivity(Intent(this , chapter::class.java).putExtra("hi" , d).putExtra("hii" , c)
               .putExtra("image",viewpagermodel.banner))
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


           FirebaseFirestore.getInstance().collection("lastadapter").whereEqualTo("maingenre",title)
               .get()
               .addOnCompleteListener {
                   if (it.isSuccessful)
                   {

                       for (d in it.result!!){

                           val a = d.get("chapterstartingfrom?") as Long
                           val b = d.get("lastchapternumber?") as Long
                           if (b-a == 0L )
                           {
                               lastlist.add(viewpagermodel(banner = d.get("comicimage") as String , vdate =d.get("comicdate") as String,
                                   vname = d.get("comicname") as String , b1 =  "chapter-${d.get("lastchapternumber?") as Long}" as String ,
                                   b2 = null
                               ))
                           }
                           else
                           {
                               lastlist.add(viewpagermodel(banner = d.get("comicimage") as String , vdate =d.get("comicdate") as String,
                                   vname = d.get("comicname") as String , b1 =  "chapter-${d.get("lastchapternumber?") as Long}" as String ,
                                   b2= "chapter-${d.get("lastchapternumber?") as Long-1}" as String
                               ))
                           }

                           lastadapter.notifyDataSetChanged()
                       }
                       lastadapter.notifyDataSetChanged()
                   }
                   else{
                       val e = it.exception?.message
                       Toast.makeText(this , e , Toast.LENGTH_SHORT).show()
                   }

               }




        listrv.layoutManager = LinearLayoutManager(this)
        listrv.adapter = lastadapter
        lastadapter.notifyDataSetChanged()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home ->{
             startActivity(Intent(this , MainActivity2::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}