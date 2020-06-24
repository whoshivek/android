package com.shivek.mymallfinal

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
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
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlin.properties.Delegates


class MainActivity2 : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
private var current: Int?= null
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.drawer_layout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.menu.getItem(0).setChecked(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_mymall ->
                {
                    current=0
                    invalidateOptionsMenu()
                    loadfragment(HomeFragment())
                    drawerLayout.closeDrawer(Gravity.START)
                    true
                }
                R.id.nav_myorder->
                {

                    true
                }
                R.id.nav_signout ->
                {
                    true
                }
                R.id.nav_myaccount ->
                {
                    true
                }
                R.id.nav_mycart->
                {
                    current=1
                    invalidateOptionsMenu()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    loadfragment(mycart())
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
                nav_view.menu.getItem(2).setChecked(true)
         loadfragment(mycart())
            }
            R.id.menu_search ->
            {

            }


        }
        return super.onOptionsItemSelected(item)
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun loadfragment(fragment : Fragment)
    {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment , fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (current==1)
        {
           Toast.makeText(this , "no action" , Toast.LENGTH_SHORT).show()
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