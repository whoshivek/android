package com.shivek.myntracopy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    loadFragment(shop())

bottom.setOnNavigationItemSelectedListener {
    when(it.itemId)
    {
        R.id.homeFragment ->
        {
            supportFragmentManager
               loadFragment(shop())
            true
        }

        R.id.momentsFragment ->{

            loadFragment(favorite())
            true
        }
        else ->false
    }

}


    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}