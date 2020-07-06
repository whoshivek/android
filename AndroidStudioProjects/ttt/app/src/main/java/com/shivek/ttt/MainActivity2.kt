package com.shivek.ttt

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationView

import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private var current: Int?= null
    private lateinit var mAdView: AdView

    private val mAppUnitId: String by lazy {

        "ca-app-pub-ca-app-pub-ca-app-pub-8822526167094562~6749849483"
    }
    private lateinit var  drawerLayout: DrawerLayout
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        drawerLayout = findViewById(R.id.drawer)



        mAdView = findViewById(R.id.adView)

        initializeBannerAd(mAppUnitId)

        loadBannerAd()
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.menu.getItem(0).setChecked(true)

        nav.setOnClickListener {
            if (!drawerLayout?.isDrawerOpen(Gravity.START))
            {
                drawerLayout.openDrawer(Gravity.START)
            }
        }

navView.setNavigationItemSelectedListener {
    when(it.itemId)
    {
        R.id.nav_aboutus->{
            current = 1
            navv.text = "About Us"
                    loadfragment(Aboutus())
            drawerLayout.closeDrawer(Gravity.START)
            true
        }
        R.id.nav_home ->{
            navv.text = "Twilight Scans"
            loadfragment(home())
            drawerLayout.closeDrawer(Gravity.START)
            true
        }


        else ->
            false
    }
}
        loadfragment(home())
    }

    private fun initializeBannerAd(mAppUnitId: String) {
        MobileAds.initialize(this, mAppUnitId)
    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun loadfragment(home: Fragment) {
          supportFragmentManager
              .beginTransaction()
              .replace(R.id.container , home)
              .addToBackStack(null)
              .commit()
    }


    override fun onBackPressed() {
        if (current==1)
        {
navv.text = "Twilight Scans"
            nav_view.menu.getItem(0).setChecked(true)

            current=0
     loadfragment(home())
            }
        else
        {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search,menu)
        return true
    }

}