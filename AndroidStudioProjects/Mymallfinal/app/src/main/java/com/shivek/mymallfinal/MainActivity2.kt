package com.shivek.mymallfinal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.shivek.mymallfinal.adapterandmodels.loginactivity
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


class MainActivity2 : AppCompatActivity() {
   private lateinit var  drawerLayout: DrawerLayout
private var current: Int?= null


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        drawerLayout = findViewById(R.id.drawer_layout)

        val navView: NavigationView = findViewById(R.id.nav_view)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    
        navView.menu.getItem(0).setChecked(true)


        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_mymall ->
                {
                    current=0
                    invalidateOptionsMenu()
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    nav.visibility = View.VISIBLE
                    navv.visibility = View.VISIBLE
                    loadfragment(HomeFragment())

                    drawerLayout.closeDrawer(Gravity.START)
                    true
                }
                R.id.nav_myorder->
                {
                    current=1
                    invalidateOptionsMenu()
                    nav.visibility = View.GONE
                    navv.visibility = View.GONE
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    loadfragment(myorderfragment())
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                    supportActionBar?.setTitle("My Orders")
                    drawerLayout.closeDrawer(Gravity.START)
                    true
                }
                R.id.nav_signout ->
                {
                    startActivity(Intent(this, loginactivity::class.java))
                    true
                }
                R.id.nav_myaccount ->
                {    current=1
                    invalidateOptionsMenu()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    loadfragment(myaccount())
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                    supportActionBar?.setTitle("My Account")
                    drawerLayout.closeDrawer(Gravity.START)
                    nav.visibility = View.GONE
                    navv.visibility = View.GONE
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    true
                }
                R.id.nav_mycart->
                {
                    current=1
                    invalidateOptionsMenu()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    loadfragment(mycart())
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                    supportActionBar?.setTitle("My Cart")
                    drawerLayout.closeDrawer(Gravity.START)
                    nav.visibility = View.GONE
                    navv.visibility = View.GONE
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    true
                }
                R.id.nav_myfavorites->{
                    current=1
                    invalidateOptionsMenu()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    loadfragment(mywishlist())
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                    nav.visibility = View.GONE
                    navv.visibility = View.GONE
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    supportActionBar?.setTitle("My Favorites")
                    drawerLayout.closeDrawer(Gravity.START)
                    true
                }
                R.id.nav_myrewards ->{

                    current=1
                    invalidateOptionsMenu()
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                    supportActionBar?.setTitle("My Rewards")
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    loadfragment(myrewards())
                    nav.visibility = View.GONE
                    navv.visibility = View.GONE
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    drawerLayout.closeDrawer(Gravity.START)
                    true
                }

                else ->
                    false


            }
        }
      nav.setOnClickListener {
          if (!drawerLayout?.isDrawerOpen(Gravity.START))
          {
              drawerLayout.openDrawer(Gravity.START)
          }
      }
 loadfragment(HomeFragment())
        val v = navView.getHeaderView(0)
        val act = GoogleSignIn.getLastSignedInAccount(this)
        if (act!=null)
        {
            v.texxt.text = "Hello ,${act.givenName}"
            Glide.with(this).load(act.photoUrl).into(v.imageprofile)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (current==1)
        {
          return  true
        }
                menuInflater.inflate(R.menu.main_activity2, menu)
                return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_bell ->
            {

            }
            R.id.menu_cart ->{
                invalidateOptionsMenu()
          current = 1
                supportActionBar?.setDisplayShowTitleEnabled(true)
                supportActionBar?.setTitle("My Cart")
                nav.visibility = View.GONE
                navv.visibility = View.GONE
                nav_view.menu.getItem(2).setChecked(true)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
         loadfragment(mycart())
            }
            R.id.menu_search ->
            {

            }
     android.R.id.home ->{
         current=0
         invalidateOptionsMenu()
         nav_view.menu.getItem(0).setChecked(true)
         supportActionBar?.setDisplayShowTitleEnabled(false)
         supportActionBar?.setDisplayHomeAsUpEnabled(false)
         loadfragment(HomeFragment())
         drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
         nav.visibility = View.VISIBLE
         navv.visibility = View.VISIBLE
     }

        }
        return super.onOptionsItemSelected(item)
    }




    fun loadfragment(fragment : Fragment)
    {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container , fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (current==1)
        {
            current=0
            nav_view.menu.getItem(0).setChecked(true)
            invalidateOptionsMenu()
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            loadfragment(HomeFragment())
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            nav.visibility = View.VISIBLE
            navv.visibility = View.VISIBLE
        }
        else {

            exitdialog()
        }
    }
    private fun exitdialog() {
        iOSDialogBuilder(this).apply {
            setTitle("WhatsDirect")
            setSubtitle("Are you sure to exit the app?")
            setBoldPositiveLabel(false)
            setCancelable(false)
            setPositiveListener("YES", iOSDialogClickListener {
                finishAffinity()
                it.dismiss()
            })
            setNegativeListener("NO", iOSDialogClickListener {
                it.dismiss()
            })

        }
            .build()
            .show()
    }
}