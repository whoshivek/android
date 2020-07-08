package com.shivek.ttt

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_addcoin.*
import kotlinx.android.synthetic.main.fragment_addcoin.view.*


class addcoin : Fragment() {


    private lateinit var rewardedAd: RewardedAd

    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val v= inflater.inflate(R.layout.fragment_addcoin, container, false)
        rewardedAd = RewardedAd(activity,
            "ca-app-pub-3940256099942544/5224354917")
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
                        chapter.coins = chapter.coins + 50
                        edit?.putInt("coin", chapter.coins)
                        edit?.apply()
                         Toasty.success(activityContext , "Bravo,50 Coins Added", Toast.LENGTH_LONG , true).show()
                        startActivity(Intent(activityContext , MainActivity2::class.java).putExtra("codee",25))
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










        return v
    }


    }



