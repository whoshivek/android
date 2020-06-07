package com.shivek.whatsappproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.drawerlayout
import kotlinx.android.synthetic.main.activity_main2.menutry

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var i : String? = null
        var j : String? = null


        ccc.setOnCountryChangeListener {
            i = ccc.selectedCountryName
            j = ccc.selectedCountryNameCode.toString()
        }

if (i.isNullOrBlank())
{
    i = "India"
}


        set.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("ccp", j).apply()
            startActivity(Intent(this , MainActivity::class.java))
            finish()
            Toast.makeText(this , "Now, ${i}(${ccc.selectedCountryCodeWithPlus}) is your default country" , Toast.LENGTH_LONG).show()
        }
        PreferenceManager.getDefaultSharedPreferences(this).getString("ccp" , "IN")?.let {
            ccc.setDefaultCountryUsingNameCodeAndApply(
                it
            )
        }
        menutry.setOnClickListener {
            if (!drawerlayout.isDrawerOpen(Gravity.START))
            {
                drawerlayout.openDrawer(Gravity.START)
            }
        }
        nav_viewtwo.menu.getItem(1).setTitle("My Country - ${defaultcc} (${defaultcp})")
        nav_viewtwo.menu.getItem(1).setChecked(true)
        nav_viewtwo.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_home ->
                {
                    startActivity(Intent(this , MainActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_gallery ->
                { drawerlayout.closeDrawer(Gravity.START )

                    true

                }
                else -> true
            }
        }

    }

    override fun onBackPressed() {
       startActivity(Intent(this , MainActivity::class.java))
        finish()
    }


}

