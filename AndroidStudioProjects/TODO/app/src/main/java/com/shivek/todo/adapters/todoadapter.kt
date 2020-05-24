package com.shivek.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter.view.*
import kotlinx.android.synthetic.main.addtask.*
import kotlinx.android.synthetic.main.addtask.view.*
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class todoadapter(val list: List<TodoUSER>) : RecyclerView.Adapter<todoadapter.todoholder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoholder {
        return todoholder(LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter ,parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: todoholder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }


    class todoholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todoUSER: TodoUSER) {
            with(itemView){
                val colors = resources.getIntArray(R.array.randomcolor)
                val randomColor = colors[Random().nextInt(colors.size)]
                view.setBackgroundColor(randomColor)
                title.text = todoUSER.title
              description.text = todoUSER.description
            category.text = todoUSER.category



               updatedate(todoUSER.date)
                updatetime(todoUSER.time)

            }
        }

        private fun updatetime(time: Long) {
            val myformat = "h : mm a"
            val sdf = SimpleDateFormat(myformat)

            itemView.ettime.text = sdf.format(Date(time))
        }

        private fun updatedate(date: Long) {
            val myformat = "EEE, dd MMM yyyy"
            val sdf = SimpleDateFormat(myformat)

              itemView.etdate.text = sdf.format(java.util.Date(date))
        }

    }
    }