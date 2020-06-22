package com.shivek.a7821048896project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shivek.a7821048896project.roomdb.database
import com.shivek.a7821048896project.roomdb.modelclass
import kotlinx.android.synthetic.main.activity_insertdetail.*
import kotlinx.android.synthetic.main.recyclerviewdesign.*
import kotlinx.android.synthetic.main.recyclerviewdesign.tv1
import kotlinx.android.synthetic.main.recyclerviewdesign.tv2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val DB_CLASS = "todo.db"
class Insertdetail : AppCompatActivity() {

    val db by lazy{
        database.getDatabase(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertdetail)

save.setOnClickListener {
    setdata()
    finish()
}
    }

    private fun setdata() {
        val a = tv1.text.toString()
        val b = tv2.text.toString()

        GlobalScope.launch (Dispatchers.Main){
            val id = withContext(Dispatchers.IO){
                return@withContext db.TODOdao().insert(modelclass(
                    a,
                    b
                ))
            }
        }
    }
}