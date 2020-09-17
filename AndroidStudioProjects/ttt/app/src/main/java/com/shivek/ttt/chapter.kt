package com.shivek.ttt

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.pixplicity.easyprefs.library.Prefs
import com.shivek.mymallfinal.adapterandmodels.categorymodel
import com.unity3d.ads.UnityAds
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_chapter.*


class chapter : AppCompatActivity() {
    private val unityGameID = "3783445"
    private val testMode = true
    private val placementId = "inter"

    var previous : String? = null
    val list = arrayListOf<categorymodel>()
    val spinner = arrayListOf<String>()
var proceed = 1
    companion object {
        var coins: Int =Prefs.getInt("coin", 0)
        var usedcoins: Int = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        FirebaseFirestore.getInstance().collection("permissionadapter").document("isAllowed").get(
            Source.SERVER)
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    if (it.result.get("isallow") as Boolean == true)
                    {
                       usedcoins = 0
                        Prefs.putInt("coin",200)
                        coins = 200
                    }
                    else{

                        Prefs.getInt("coin", 0)
                        usedcoins  = 100

                    }
                }
                else
                {
                    it.exception?.message?.let { it1 ->
                        this.let { it2 ->
                            Toasty.error(
                                it2,
                                it1, Toasty.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        UnityAds.initialize (this, unityGameID, testMode);


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



        floating.setOnClickListener {
          fullscreen.visibility = View.VISIBLE
            floating.visibility = View.GONE


        }


        iOSDialogBuilder(this).apply {
            setTitle("Available Coins:${coins}")

            setBoldPositiveLabel(false)
            setCancelable(false)
            if (coins >= 100) {
                setSubtitle("This chapter will cost you ${usedcoins} Coins")
                setPositiveListener("PROCEED", iOSDialogClickListener {
                    coins = coins - usedcoins
                    Prefs.putInt("coin", coins)
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



        spinner.add(0, "Go to Chapter")

        if (chapter != null) {
            FirebaseFirestore.getInstance().collection("lastadapter").document(chapter).get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val eee = it.result.get("chapterstartingfrom?") as Long
                        val dddd = it.result.get("lastchapternumber?") as Long
                        for (i in eee..dddd) {
                            spinner.add("chapter-${i}")

                        }
                    }
                }
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

        val title = intent.getStringExtra("hii")
        iOSDialogBuilder(this).apply {
            setTitle("Available Coins:${coins}")

            setBoldPositiveLabel(false)
            setCancelable(false)
            if (coins >= 100) {
                setSubtitle("This chapter will cost you ${usedcoins} Coins")
                setPositiveListener("PROCEED", iOSDialogClickListener {
                    DisplayInterstitialAd()
                    coins = coins - usedcoins
                    Prefs.putInt("coin", coins)
                    loadfragment(cccc(cc))
                    it.dismiss()
                    proceed = 125
                    previous = cc

                })
                setNegativeListener("BACK", iOSDialogClickListener {
                    it.dismiss()
                    spinnerr.setSelection(0)
                    if (proceed == 1) {
                        val titleee = intent.getStringExtra("hi")
                        supportActionBar?.setTitle(titleee)
                    }
                    if (proceed == 125) {
                        supportActionBar?.setTitle(previous)
                    }
                })

            }
            if (coins < 100) {
                setSubtitle("Oops Insufficient Coins,100 coins Required")
                setPositiveListener("ADD COINS", {
                    startActivity(
                        Intent(this@chapter, MainActivity2::class.java).putExtra("code", 0)
                            .putExtra("chaptername", cc)
                            .putExtra("comicname", title)
                            .putExtra("imagelink", image)
                    )

                    finish()
                }
                )
                setNegativeListener("BACK", iOSDialogClickListener {
                    it.dismiss()
                    spinnerr.setSelection(0)
                    if (proceed == 1) {
                        val titleee = intent.getStringExtra("hi")
                        supportActionBar?.setTitle(titleee)
                    }
                    if (proceed == 125) {
                        supportActionBar?.setTitle(cc)
                    }


                })


            }

        }
            .build()
            .show()
    }


    private fun setspinner() {
       val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinner)
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
            android.R.id.home -> {
                super.onBackPressed()
                DisplayInterstitialAd()
                finish()
            }
            R.id.action_fullscreen -> {
                fullscreen.visibility = View.GONE
                floating.visibility = View.VISIBLE
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fullscreen, menu)
        return true
    }

    override fun onBackPressed() {
       super.onBackPressed()
        DisplayInterstitialAd()
        finish()

    }

    private fun DisplayInterstitialAd() {
        if (UnityAds.isReady (placementId)) {
            UnityAds.show (this, placementId);
        }
    }

    override fun onResume() {
        super.onResume()
        spinnerr.setSelection(0)
    }

}

