package com.shivek.ttt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener

import com.google.firebase.storage.FirebaseStorage
import com.shivek.mymallfinal.adapterandmodels.categorymodel
import com.shivek.ttt.adaptersandmodel.chapteradapter
import kotlinx.android.synthetic.main.activity_chapter.*


class chapter : AppCompatActivity() {

    val list = arrayListOf<categorymodel>()
    val adapter = chapteradapter(list)
    val spinner = arrayListOf<String>()

    companion object {
        var coins: Int = 1000
        var usedcoins: Int = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)


        var title = intent.getStringExtra("hi")
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setTitle(title)

        val chapter = intent.getStringExtra("hii")
        chaptername.text = chapter
        ///////////////////////

        val image = intent.getStringExtra("image")

        val sp = getSharedPreferences("sp", Context.MODE_PRIVATE)
        coins = sp.getInt("coin", coins)


        iOSDialogBuilder(this).apply {
            setTitle("Available Coins:${coins}")

            setBoldPositiveLabel(false)
            setCancelable(false)
            if (coins >= 100) {
                setSubtitle("This chapter will cost you ${usedcoins} Coins")
                setPositiveListener("PROCEED", iOSDialogClickListener {
                    val edit = sp.edit()
                    coins = coins - usedcoins
                    edit.putInt("coin", coins)
                    edit.apply()
                    loadfragment(cccc(title))
                    it.dismiss()
                })
                setNegativeListener("BACK", iOSDialogClickListener {
                    it.dismiss()
                    finish()
                })

            }
            if (coins < 100) {
                setSubtitle("Oops Insufficient Coins,100 coins Required")
                setPositiveListener("ADD COINS", {
                    startActivity(
                        Intent(this@chapter, MainActivity2::class.java).putExtra("code", 0)
                            .putExtra("chaptername", title)
                            .putExtra("comicname", chapter)
                            .putExtra("imagelink", image)
                    )

                    finish()
                }
                )
                setNegativeListener("BACK", iOSDialogClickListener {
                    it.dismiss()
                    finish()
                })


            }

        }
            .build()
            .show()

        FirebaseStorage.getInstance().getReference("${chapter}${title}").listAll()
            .addOnSuccessListener {
                val e = it.items.size
                for (d in 1..e) {
                    list.add(categorymodel(link = "gs://twilightscans-9660c.appspot.com/${chapter}${title}/c${d}.jpg" as String))
                    adapter.notifyDataSetChanged()
                }

            }

        spinner.add(0, "Choose Chapter")


        for (i in 1..23) {
            spinner.add("chapter-${i}")
        }
        setspinner()

        spinnerr.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {

                    } else {
                        val cc = parent?.getItemAtPosition(position).toString()
                            title = cc
                            alertbox(cc)
                        supportActionBar?.setTitle(title)
                    }
                }

                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                }
            }
    }

    private fun alertbox(cc: String) {
        val image = intent.getStringExtra("image")
        val sp = getSharedPreferences("sp", Context.MODE_PRIVATE)
        val title = intent.getStringExtra("hi")
        iOSDialogBuilder(this).apply {
            setTitle("Available Coins:${coins}")

            setBoldPositiveLabel(false)
            setCancelable(false)
            if (coins >= 100) {
                setSubtitle("This chapter will cost you ${usedcoins} Coins")
                setPositiveListener("PROCEED", iOSDialogClickListener {
                    val edit = sp.edit()
                    coins = coins - usedcoins
                    edit.putInt("coin", coins)
                    edit.apply()
                    loadfragment(cccc(cc))
                    it.dismiss()
                })
                setNegativeListener("BACK", iOSDialogClickListener {
                    it.dismiss()
                    spinnerr.setSelection(0)
                    val titleee = intent.getStringExtra("hi")
                    supportActionBar?.setTitle(titleee)

                })

            }
            if (coins < 100) {
                setSubtitle("Oops Insufficient Coins,100 coins Required")
                setPositiveListener("ADD COINS", {
                    startActivity(
                        Intent(this@chapter, MainActivity2::class.java).putExtra("code", 0)
                            .putExtra("chaptername", title)
                            .putExtra("comicname", cc)
                            .putExtra("imagelink", image)
                    )

                    finish()
                }
                )
                setNegativeListener("BACK", iOSDialogClickListener {
                    it.dismiss()
                    spinnerr.setSelection(0)
                })


            }

        }
            .build()
            .show()
    }


    private fun setspinner() {
       val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item , spinner)
        spinnerr.adapter = adapter
    }
    private fun loadfragment(home: Fragment) {
        if(supportFragmentManager.findFragmentById(R.id.containerrr) != null) {
            supportFragmentManager
                .beginTransaction().
                remove(supportFragmentManager.findFragmentById(R.id.containerrr)!!).commit();
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerrr, home)
            .commit();
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home ->{
           super.onBackPressed()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
       super.onBackPressed()
        finish()
    }

    override fun onResume() {
        super.onResume()
        spinnerr.setSelection(0)
    }

}

