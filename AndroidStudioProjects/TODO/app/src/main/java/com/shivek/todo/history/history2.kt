package com.shivek.todo.history

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivek.todo.R
import com.shivek.todo.TodoUSER
import com.shivek.todo.todoadapter
import com.shivek.todo.todoroomdatabase
import kotlinx.android.synthetic.main.activity_history2.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class history2 : AppCompatActivity() {


    val list = arrayListOf<TodoUSER>()
    val adapter = todoadapter(list)

    val db by lazy {
        todoroomdatabase.getDatabase(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history2)

        rvv.layoutManager = LinearLayoutManager(this)
        rvv.adapter = adapter


        db.tododau().history().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
            else{
                list.clear()
                adapter.notifyDataSetChanged()
            }

        })

        swipe()
    }

    private fun swipe() {
        val simpleitemtouch =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }


                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE)
                    {
                        val itemView = viewHolder.itemView
                        val paint = Paint()
                        val icon: Bitmap



                        if (dX<0) {
                            icon = BitmapFactory.decodeResource(resources , R.mipmap.ic_delete_white_png)

                            paint.color = Color.parseColor("#F70D1A")
                            c.drawRect(
                                itemView.right.toFloat()+ dX,
                                itemView.top.toFloat() ,
                                itemView.right.toFloat() ,
                                itemView.bottom.toFloat(),
                                paint
                            )

                            c.drawBitmap(
                                icon ,
                                itemView.right.toFloat() - icon.width.toFloat(),
                                itemView.top.toFloat() + (itemView.bottom.toFloat()   - itemView.top.toFloat() - icon.height.toFloat())/2                     ,
                                paint)

                        }
                             if (dX<0) {
                                 viewHolder.itemView.translationX = dX
                             }
                    }



                    else {

                        super.onChildDraw(
                            c,
                            recyclerView,
                            viewHolder,
                            dX,
                            dY,
                            actionState,
                            isCurrentlyActive
                        )
                    }
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition

                    if (direction == ItemTouchHelper.LEFT)
                    {
                        GlobalScope.launch(Dispatchers.IO) { db.tododau().delete(adapter.getItemId(position)) }
                        Toast.makeText(applicationContext , "DELETED" , Toast.LENGTH_SHORT).show()


                    }


                }
            }
        val r = ItemTouchHelper(simpleitemtouch)
        r.attachToRecyclerView(rvv)
    }




}

