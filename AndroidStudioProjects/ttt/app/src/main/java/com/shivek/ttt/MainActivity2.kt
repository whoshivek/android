package com.shivek.ttt

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var  drawerLayout: DrawerLayout
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        drawerLayout = findViewById(R.id.drawer)

        val navView: NavigationView = findViewById(R.id.nav_view)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.menu.getItem(0).setChecked(true)

        nav.setOnClickListener {
            if (!drawerLayout?.isDrawerOpen(Gravity.START))
            {
                drawerLayout.openDrawer(Gravity.START)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search,menu)
        return true
    }

}