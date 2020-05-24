package com.shivek.todo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.addtask.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*



const val DB_CLASS = "todo.db"
class task : AppCompatActivity(), View.OnClickListener {
    lateinit var mycalender: Calendar
     var finaldate : Long? = null
    var finaltime : Long? =null


    lateinit var dateStListener: DatePickerDialog.OnDateSetListener
    lateinit var TIMEStListener: TimePickerDialog.OnTimeSetListener

    private val list = arrayListOf("Default","Personal","Businees", "Shopping", "Fitness" , "Education","Wishlist")


    val db by lazy {
       todoroomdatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtask)

        editdate.setOnClickListener(this)
        edittt.setOnClickListener(this)
        savedata.setOnClickListener(this)
        setspinner()
    }

    override fun onClick(v: View) {
        when(v.id){
           R.id.editdate ->{
               setdate()
           }
            R.id.edittt ->{
                setTIME()
            }
            R.id.savedata ->{

                val i = Intent(this,MainActivity::class.java)

                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(i)

                setdata()
            }

        }
    }

    private fun setdata() {

        val t = t.text.toString()
        val d = d.text.toString()


        val catego = spinner.selectedItem.toString()

        GlobalScope.launch (Dispatchers.Main){
            val id = withContext(Dispatchers.IO){
               return@withContext db.tododau().insert(TodoUSER(
                   if(t.isNullOrEmpty())
                   "NO TITLE"
                   else t,
                   d,
                   (if (finaldate == null)
                       System.currentTimeMillis()
                   else  finaldate)!!,
                   (if (finaltime == null)
                       System.currentTimeMillis()
                   else finaltime)!!,
                   catego))
            }
        }


    }

    private fun setspinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item , list)
            spinner.adapter = adapter

    }

    private fun setTIME() {
       mycalender = Calendar.getInstance()

        TIMEStListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            mycalender.set(Calendar.HOUR_OF_DAY , hourOfDay)
            mycalender.set(Calendar.MINUTE, minute)
            updatetime()

        }

        val TimePickerDialog = TimePickerDialog(
            this,
            TIMEStListener,
            mycalender.get(Calendar.HOUR_OF_DAY),
            mycalender.get(Calendar.MINUTE),
            false
        )

        TimePickerDialog.show()

    }

    private fun updatetime() {
         val myformat = "h : mm a"
        val sdf = SimpleDateFormat(myformat)

        finaltime = mycalender.time.time

        edittt.setText(sdf.format(mycalender.time))

    }

    private fun setdate() {
        mycalender= Calendar.getInstance()

        dateStListener=
     DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

         mycalender.set(Calendar.YEAR,year)
         mycalender.set(Calendar.MONTH,month)
         mycalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
         updatedate()
     }

        val DatePickerDialog = DatePickerDialog(
            this,dateStListener ,
        mycalender.get(Calendar.YEAR) , mycalender.get(Calendar.MONTH),
            mycalender.get(Calendar.DAY_OF_MONTH))

        DatePickerDialog.datePicker.minDate = System.currentTimeMillis()
        DatePickerDialog.show()
    }

    private fun updatedate() {
        val myformat = "EEE, dd MMM yyyy"
        val sdf = SimpleDateFormat(myformat)
       finaldate = mycalender.time.time

        editdate.setText(sdf.format(mycalender.time))


        edittime.visibility = View.VISIBLE



    }
}


