package com.shivek.todo


import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val list = arrayListOf<TodoUSER>()
    var adapter = todoadapter(list)

    val db by lazy {
        todoroomdatabase.getDatabase(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        val isFirst =
            getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isfirstrun", true)
        if (isFirst) {
            //show sign up activity
            startActivity(Intent(this@MainActivity, launchactivity::class.java))

        }


        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
            .putBoolean("isfirstrun", false).apply()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvtodo.layoutManager = LinearLayoutManager(this)
        rvtodo.adapter = adapter

        db.tododau().task().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            } else {
                list.clear()
                adapter.notifyDataSetChanged()
            }

        })

        if (adapter.itemCount>0)
        {
            nothinh.visibility = View.VISIBLE
        }
        else
        {
            nothinh.visibility = View.GONE
        }

        swipe()
    }






        fun swipe() {
            val simpleitemtouch =
                object : ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {
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
                        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                            val itemView = viewHolder.itemView
                            val paint = Paint()
                            val icon: Bitmap

                            if (dX > 0) {


                                icon = BitmapFactory.decodeResource(
                                    resources,
                                    R.mipmap.ic_check_white_png
                                )

                                paint.color = Color.parseColor("#4CC417")

                                c.drawRect(
                                    itemView.left.toFloat(),
                                    itemView.top.toFloat(),
                                    itemView.left.toFloat() + dX,
                                    itemView.bottom.toFloat(),
                                    paint
                                )

                                c.drawBitmap(
                                    icon,
                                    itemView.left.toFloat(),
                                    itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height.toFloat()) / 2,
                                    paint
                                )

                            } else {
                                icon = BitmapFactory.decodeResource(
                                    resources,
                                    R.mipmap.ic_delete_white_png
                                )

                                paint.color = Color.parseColor("#F70D1A")
                                c.drawRect(
                                    itemView.right.toFloat() + dX,
                                    itemView.top.toFloat(),
                                    itemView.right.toFloat(),
                                    itemView.bottom.toFloat(),
                                    paint
                                )

                                c.drawBitmap(
                                    icon,
                                    itemView.right.toFloat() - icon.width.toFloat(),
                                    itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height.toFloat()) / 2,
                                    paint
                                )

                            }

                            viewHolder.itemView.translationX = dX
                        } else {

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

                        if (direction == ItemTouchHelper.LEFT) {
                            GlobalScope.launch(Dispatchers.IO) {
                                db.tododau().delete(adapter.getItemId(position))
                            }
                            Toast.makeText(applicationContext, "☓ TASK DELETED", Toast.LENGTH_SHORT)
                                .show()


                        } else if (direction == ItemTouchHelper.RIGHT) {
                            GlobalScope.launch(Dispatchers.IO) {
                                db.tododau().update(adapter.getItemId(position))
                            }
                            Toast.makeText(
                                applicationContext, "✓ TASK FINISHED\n" +
                                        "check history for reference", Toast.LENGTH_SHORT
                            ).show()


                        }

                    }
                }
            val r = ItemTouchHelper(simpleitemtouch)
            r.attachToRecyclerView(rvtodo)
        }


        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)

            val item = menu.findItem(R.id.searchbar)
            val searchview = item.actionView as SearchView
            item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    displaytodo()
                    return true

                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    displaytodo()
                    return true
                }
            })

            searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!newText.isNullOrEmpty()) {
                        displaytodo(newText)
                    }
                    return true
                }
            })


            return super.onCreateOptionsMenu(menu)


        }

        fun displaytodo(n: String = "") {
            db.tododau().task().observe(this, Observer {
                if (it.isNotEmpty()) {
                    list.clear()
                    list.addAll(it.filter { todoUSER ->
                        todoUSER.title.contains(n, true)

                    })
                    adapter.notifyDataSetChanged()
                }
            })
        }

        fun Opennewtask(view: View) {

            val i = Intent(this, task::class.java)
            startActivity(i)
        }


        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {
                R.id.history -> {
                    startActivity(Intent(this, History::class.java))

                }

                R.id.about -> {
                    startActivity(Intent(this, about::class.java))

                }
            }
            return super.onOptionsItemSelected(item)
        }
    }


