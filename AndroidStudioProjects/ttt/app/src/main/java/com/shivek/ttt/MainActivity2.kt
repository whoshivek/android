package com.shivek.ttt

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.shapes.Shape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Size
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
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.material.navigation.NavigationView
import com.shivek.ttt.chapter.Companion.coins

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity2 : AppCompatActivity() {
    private var current: Int?= null
    private lateinit var mAdView: AdView
    var c = 1

    private lateinit var rewardedAd: RewardedAd

    private lateinit var mRewardedVideoAd: RewardedVideoAd

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

        rewardedAd = RewardedAd(this,
            "ca-app-pub-7686984213722052/5266706419")
        val adLoadCallback = object: RewardedAdLoadCallback() {
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

            true
        }
        R.id.nav_home ->{
            navv.text = "Twilight Scans"
            c=1
            current=0
            loadfragment(home())

            true
        }
R.id.nav_comicbook ->{
    current=1
    navv.text = "Add Coins"
    loadfragment(addcoin())

    true
}
        R.id.nav_contactus ->{
            current=1
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
          val g = getSharedPreferences("sp",Context.MODE_PRIVATE)
        val ggg = g.getInt("coin",0)

        val v = navView.getHeaderView(0)
        v.headerm.text = "COINS:${ggg}"


        if (c==1) {
            current=0
            loadfragment(home())
            navView.menu.getItem(0).setChecked(true)
        }

        c= intent.getIntExtra("codee",1)
if (c==25) {
    current=1
    c=1
    kk.build()
        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
        .setDirection(0.0, 359.0)
        .setSpeed(1f, 5f)
        .setFadeOutEnabled(true)
        .setTimeToLive(2000L)
        .addShapes(
            nl.dionsegijn.konfetti.models.Shape.Square,
            nl.dionsegijn.konfetti.models.Shape.Circle
        )
        .addSizes(nl.dionsegijn.konfetti.models.Size(12))
        .setPosition(-50f, kk.width + 50f, -50f, -50f)
        .streamFor(300, 5000L)
    navView.menu.getItem(1).setChecked(true)
    loadfragment(addcoin())
}

        c = intent.getIntExtra("code" ,1)
        if (c==0)
        {
            current=1
            c=1
            loadfragment(addcoin())
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
        if(supportFragmentManager.findFragmentById(R.id.container) != null) {
            supportFragmentManager
                .beginTransaction().
                remove(supportFragmentManager.findFragmentById(R.id.container)!!).commit();
        }
                supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, home)
            .commit();
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

    override fun onResume() {
        super.onResume()
        val g = getSharedPreferences("sp",Context.MODE_PRIVATE)
        val ggg = g.getInt("coin",0)

        val v = nav_view.getHeaderView(0)
        v.headerm.text = "COINS:${ggg}"


    }
}