package com.shivek.ttt

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener
import com.google.android.material.navigation.NavigationView
import com.pixplicity.easyprefs.library.Prefs
import com.unity3d.ads.UnityAds
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_bookname.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import java.security.AccessController.getContext

class MainActivity2 : AppCompatActivity() {
    private var current: Int? = null

    var c = 1
lateinit var search : SearchView

    private var menushow: Int? = null

    private val unityGameID = "3783445"

    private var bannerPlacement = "bannermain"
    private lateinit var bottomBanner : BannerView
    private val testMode = true

    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        UnityAds.initialize(this, unityGameID,  testMode);


        bottomBanner = BannerView(this, bannerPlacement, UnityBannerSize(320, 50))
        BannerView.CENTER_HORIZONTAL
        banner_container.addView(bottomBanner)
        bottomBanner.load()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        drawerLayout = findViewById(R.id.drawer)



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.menu.getItem(0).setChecked(true)

        nav.setOnClickListener {
            if (!drawerLayout?.isDrawerOpen(Gravity.START)) {
                drawerLayout.openDrawer(Gravity.START)
            }
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_aboutus -> {
                    current = 1
                    menushow = 1
                    invalidateOptionsMenu()

                    navv.text = "About Us"
                    loadfragment(Aboutus())

                    true
                }
                R.id.nav_home -> {
                    invalidateOptionsMenu()
                    navv.text = "Twilight Scans"
                    c = 1
                    menushow = 0
                    current = 0
                    loadfragment(home())

                    true
                }
                R.id.nav_comicbook -> {
                    current = 1
                    invalidateOptionsMenu()
                    menushow = 1
                    navv.text = "Add Coins"
                    loadfragment(addcoin())

                    true
                }
                R.id.nav_contactus -> {
                    current = 1
                    invalidateOptionsMenu()
                    menushow = 1
                    navv.text = "Contact Us"
                    loadfragment(contactus())

                    true
                }

                else ->
                    false
            }
            drawerLayout.closeDrawer(Gravity.START)
            return@setNavigationItemSelectedListener true
        }

        val ggg = Prefs.getInt("coin", 0)

        val v = navView.getHeaderView(0)
        v.headerm.text = "COINS:${ggg}"


        if (c == 1) {
            current = 0
            navv.text = "Twilight Scans"
            menushow = 0
            invalidateOptionsMenu()
            loadfragment(home())
            navView.menu.getItem(0).setChecked(true)
        }

        c = intent.getIntExtra("codee", 1)

        c = intent.getIntExtra("code", 1)
        if (c == 0) {
            invalidateOptionsMenu()
            menushow = 1
            current = 1
            c = 1
            loadfragment(addcoin())
            navv.text = "Add Coins"
            navView.menu.getItem(1).setChecked(true)
        }
    }



    private fun loadfragment(home: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.container) != null) {
            supportFragmentManager
                .beginTransaction()
                .remove(supportFragmentManager.findFragmentById(R.id.container)!!).commit();
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, home)
            .commit();
    }


    override fun onBackPressed() {

        if (current == 1) {
            invalidateOptionsMenu()
            menushow = 0
            navv.text = "Twilight Scans"
            nav_view.menu.getItem(0).setChecked(true)
            current = 0
            loadfragment(home())
        } else {


            exitdilaog()
        }

    }


    private fun exitdilaog() {
        iOSDialogBuilder(this).apply {
            setTitle("Twilight Scans")
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

    @SuppressLint("ServiceCast")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        if (menushow==0) {
            menuInflater.inflate(R.menu.search, menu)
            val item = menu?.findItem(R.id.action_search)
             search = item?.actionView as SearchView
            search.maxWidth = R.attr.tabMaxWidth
            item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {

                    return true

                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    loadfragment(home())
                    return true
                }
            })



            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!newText?.isBlank()!!) {
                        loadfragment(searchfragment(newText))
                    } else {
                        loadfragment(home())
                    }
                    return true
                }

            })
        }
        else if (menushow==1)
        {
            return false
        }
        return super.onCreateOptionsMenu(menu)
    }


    override fun onPause() {
        super.onPause()

    }

    override fun onResume() {
            super.onResume()

            val ggg = Prefs.getInt("coin", 0)

            val v = nav_view.getHeaderView(0)
            v.headerm.text = "COINS:${ggg}"


        }



}