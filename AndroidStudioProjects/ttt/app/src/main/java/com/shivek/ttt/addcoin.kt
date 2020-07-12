package com.shivek.ttt

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.os.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import com.shivek.ttt.adaptersandmodel.listrecycle
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_addcoin.*
import kotlinx.android.synthetic.main.fragment_addcoin.view.*
import kotlinx.android.synthetic.main.fragment_addcoin.view.discord
import kotlinx.android.synthetic.main.fragment_addcoin.view.patreon
import kotlinx.android.synthetic.main.fragment_addcoin.view.paypal
import kotlinx.android.synthetic.main.fragment_home.view.*


class addcoin : Fragment() {


    private lateinit var rewardedAd: RewardedAd

    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val v= inflater.inflate(R.layout.fragment_addcoin, container, false)
        rewardedAd = RewardedAd(activity,
            "ca-app-pub-7686984213722052/5266706419")
        val adLoadCallback = object: RewardedAdLoadCallback() {
            override fun onRewardedAdLoaded() {

            }
            override fun onRewardedAdFailedToLoad(errorCode: Int) {

            }
        }
        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)

        v.add.setOnClickListener {
            if (rewardedAd.isLoaded) {
                val activityContext: Activity = this.requireActivity()
                val adCallback = object: RewardedAdCallback() {
                    override fun onRewardedAdOpened() {

                    }
                    override fun onRewardedAdClosed() {
                        startActivity(Intent(activityContext , MainActivity2::class.java))
                        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
                        v.how.visibility = View.GONE
                    }
                    override fun onUserEarnedReward(@NonNull reward: RewardItem) {
                        val g = this@addcoin.activity?.getSharedPreferences("sp", Context.MODE_PRIVATE)
                        val edit = g?.edit()

                        val comicname =activity?.intent?.getStringExtra("comicname")
                        val imagelink =activity?.intent?.getStringExtra("imagelink")

                        val chaptername =activity?.intent?.getStringExtra("chaptername")
                        chapter.coins = chapter.coins + 100
                        edit?.putInt("coin", chapter.coins)
                        edit?.apply()
                         Toasty.success(activityContext , "Bravo,100 Coins Added", Toast.LENGTH_LONG , true).show()
                        startActivity(Intent(activityContext , MainActivity2::class.java).putExtra("codee",25)
                            .putExtra("comicname",comicname)
                            .putExtra("chaptername",chaptername)
                            .putExtra("imagelink",imagelink)

                        )
                        activity?.finish()



                    }
                    override fun onRewardedAdFailedToShow(errorCode: Int) {
                        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
                    }
                }
                rewardedAd.show(activityContext, adCallback)
            }
            else {
             Toasty.error(requireContext(),"try after some time" , Toast.LENGTH_SHORT , true).show()
                rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
             v.how.visibility = View.VISIBLE
            }

        }
        val g = this.activity?.getSharedPreferences("sp", Context.MODE_PRIVATE)
        val ggg = g?.getInt("coin",0)
        v.balance.text = ggg.toString()


        v.discord.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://discord.com/invite/eH4y7eZ")
            startActivity(i)
        }

        v.paypal.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.paypal.me/MANGANIC007")
            startActivity(i)
        }

        v.patreon.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.patreon.com/twilightscanlations")
            startActivity(i)


        }

        val comicname =activity?.intent?.getStringExtra("comicname")
        val imagelink =activity?.intent?.getStringExtra("imagelink")

        val chaptername =activity?.intent?.getStringExtra("chaptername")


        if (chaptername.isNullOrBlank())
        {
            v.linear.visibility = View.GONE
        }

        if (chapter.coins>=100)
        {
            v.tech.text = "Enjoy your chapter"
            v.tech.setTextColor(Color.parseColor("#4BB543"))
        }
val list = arrayListOf<viewpagermodel>()
        list.add(viewpagermodel(banner = imagelink,vname =comicname ,b1 = chaptername))
        val adapter = listrecycle(list,{viewpagermodel ->clickkk(viewpagermodel)  },{viewpagermodel ->clickkkk(viewpagermodel)  }
        , {viewpagermodel ->sclick(viewpagermodel)  })
         v.historyrecycle.layoutManager = LinearLayoutManager(activity)
        v.historyrecycle.adapter= adapter
        adapter.notifyDataSetChanged()





        return v
    }

    private fun sclick(viewpagermodel: viewpagermodel) {
         startActivity(Intent(activity , boookkk::class.java).putExtra("bookname",viewpagermodel.vname))
    }

    private fun clickkkk(viewpagermodel: viewpagermodel) {

    }


    private fun clickkk(viewpagermodel: viewpagermodel) {
        startActivity(Intent(activity,boookkk::class.java))
    }


}



