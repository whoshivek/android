package com.shivek.ttt

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.gdacciaro.iOSDialog.iOSDialogClickListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.material.navigation.NavigationView

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity2 : AppCompatActivity() {
    private var current: Int? = null
    private lateinit var mAdView: AdView
    var c = 1
lateinit var search : SearchView
    private lateinit var rewardedAd: RewardedAd
    private var menushow: Int? = null
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    private val mAppUnitId: String by lazy {

        "ca-app-pub-8822526167094562~6749849483"
    }
    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        drawerLayout = findViewById(R.id.drawer)
        MobileAds.initialize(this)
        rewardedAd = RewardedAd(
            this,
            "ca-app-pub-8822526167094562/5006103533"
        )
        val adLoadCallback = object : RewardedAdLoadCallback() {
            override fun onRewardedAdLoaded() {

            }

            override fun onRewardedAdFailedToLoad(errorCode: Int) {

            }
        }
        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)

        mAdView = findViewById(R.id.adView)

        initializeBannerAd(mAppUnitId)

        loadBannerAd()
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
        val g = getSharedPreferences("sp", Context.MODE_PRIVATE)
        val ggg = g.getInt("coin", 0)

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

    private fun initializeBannerAd(mAppUnitId: String) {
        MobileAds.initialize(this, mAppUnitId)
    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
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
                 if (!newText?.isBlank()!!)
                 {
                     loadfragment(searchfragment(newText))
                 }
                    else
                 {
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
        MobileAds.initialize(this)
        loadBannerAd()
        val adLoadCallback = object : RewardedAdLoadCallback() {
            override fun onRewardedAdLoaded() {

            }

            override fun onRewardedAdFailedToLoad(errorCode: Int) {

            }
        }
        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
    }

    override fun onResume() {
            super.onResume()
            val g = getSharedPreferences("sp", Context.MODE_PRIVATE)
            val ggg = g.getInt("coin", 0)
            MobileAds.initialize(this)
            loadBannerAd()
            val adLoadCallback = object : RewardedAdLoadCallback() {
                override fun onRewardedAdLoaded() {

                }

                override fun onRewardedAdFailedToLoad(errorCode: Int) {

                }
            }
            rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
            val v = nav_view.getHeaderView(0)
            v.headerm.text = "COINS:${ggg}"


        }

}