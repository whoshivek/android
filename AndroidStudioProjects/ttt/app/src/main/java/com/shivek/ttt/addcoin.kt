package com.shivek.ttt
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.pixplicity.easyprefs.library.Prefs
import com.shivek.mymallfinal.adapterandmodels.viewpagermodel
import com.shivek.ttt.adaptersandmodel.listrecycle
import com.unity3d.ads.IUnityAdsListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAds.FinishState
import com.unity3d.ads.UnityAds.UnityAdsError
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBanners.destroy
import com.unity3d.services.banners.bridge.BannerBridge.destroy
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_addcoin.*
import kotlinx.android.synthetic.main.fragment_addcoin.view.*
import java.lang.String.format
import java.text.DateFormat
import java.text.DateFormat.DATE_FIELD
import java.util.*
import kotlin.time.ExperimentalTime


class addcoin : Fragment() {

    private lateinit var textt : TextView

    private lateinit var text : TextView
   private lateinit var abc: IUnityAdsListener
    lateinit var addc : Button
    private val unityGameID = "3783445"
    private val testMode = true
    private val placementId = "rewardedVideo"


    @ExperimentalTime
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val v= inflater.inflate(R.layout.fragment_addcoin, container, false)
        FirebaseFirestore.getInstance().collection("permissionadapter").document("isAllowed").get(
            Source.SERVER)
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    if (it.result.get("isallow") as Boolean == true)
                    {
                        v.emergency.visibility = View.VISIBLE
                    }
                    else{



                    }
                }
                else
                {
                    it.exception?.message?.let { it1 ->
                        activity.let { it2 ->
                            if (it2 != null) {
                                Toasty.error(
                                    it2,
                                    it1, Toasty.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }

        //Add the listener to the SDK:
        //Add the listener to the SDK:


        UnityAds.initialize(activity, unityGameID, testMode);
        addc= v.add
addc.setOnClickListener {
    DisplayRewardedAd ();
    v.add.isEnabled = true

}

      abc = object : IUnityAdsListener {
            override fun onUnityAdsReady(s: String) {

                addc.isEnabled = true
            }
            override fun onUnityAdsStart(s: String) {
                addc.isEnabled = true
            }
            override fun onUnityAdsFinish(s: String, finishState: FinishState) {
                if (finishState == FinishState.COMPLETED)
                {
                    chapter.coins = chapter.coins + 100
                    Prefs.putInt("coin", chapter.coins)
                    activity?.let {
                        Toasty.success(
                            it,
                            "Bravo,100 Coins Added",
                            Toast.LENGTH_LONG,
                            true
                        ).show()
                    }
                }
                if (finishState == FinishState.SKIPPED)
                {
                    //nothing
                }
                if (finishState == FinishState.ERROR)
                {
                    //nothing
                }
            }
            override fun onUnityAdsError(unityAdsError: UnityAdsError, s: String) {
                addc.isEnabled = true
                Toasty.error(requireContext(), "try after some time", Toast.LENGTH_SHORT, true).show()
                v.how.visibility = View.VISIBLE
            }
        }
UnityAds.setListener(abc)

      /* v.add.setOnClickListener {
            if (rewardedAd.isLoaded) {
                val activityContext: Activity = this.requireActivity()
                val adCallback = object: RewardedAdCallback() {
                    override fun onRewardedAdOpened() {

                    }
                    override fun onRewardedAdClosed() {
                        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
                        v.how.visibility = View.GONE
                    }
                    override fun onUserEarnedReward(@NonNull reward: RewardItem) {



                        chapter.coins = chapter.coins + 100
                        Prefs.putInt("coin", chapter.coins)
                         Toasty.success(
                             activityContext,
                             "Bravo,100 Coins Added",
                             Toast.LENGTH_LONG,
                             true
                         ).show()







                    }
                    override fun onRewardedAdFailedToShow(errorCode: Int) {
                        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
                    }
                }
                rewardedAd.show(activityContext, adCallback)
            }
            else {
             Toasty.error(requireContext(), "try after some time", Toast.LENGTH_SHORT, true).show()
                rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
             v.how.visibility = View.VISIBLE
            }

        }*/
        val ggg = Prefs.getInt("coin", 0)
        text = v.balance
        v.balance.text = ggg.toString()


        v.discord.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://discord.com/invite/eH4y7eZ")
            startActivity(i)
        }



        val comicname =activity?.intent?.getStringExtra("comicname")
        val imagelink =activity?.intent?.getStringExtra("imagelink")

        val chaptername =activity?.intent?.getStringExtra("chaptername")


        if (chaptername.isNullOrBlank())
        {
            v.linear.visibility = View.GONE
        }
        textt = v.tech
        if (chapter.coins>=100)
        {

            v.tech.text = "Enjoy your chapter"
            v.tech.setTextColor(Color.parseColor("#4BB543"))
        }
val list = arrayListOf<viewpagermodel>()
        list.add(viewpagermodel(banner = imagelink, vname = comicname, b1 = chaptername))
        val adapter = listrecycle(list,
            { viewpagermodel -> clickkk(viewpagermodel) },
            { viewpagermodel ->
                clickkkk(
                    viewpagermodel
                )
            },
            { viewpagermodel -> sclick(viewpagermodel) })
         v.historyrecycle.layoutManager = LinearLayoutManager(activity)
        v.historyrecycle.adapter= adapter
        adapter.notifyDataSetChanged()




        return v
    }



    private fun DisplayRewardedAd() {
        if (UnityAds.isReady(placementId)) {
            UnityAds.show(activity, placementId);

        }
        else{
            Toasty.error(requireContext(), "try after some time", Toast.LENGTH_SHORT, true).show()
            how.visibility = View.VISIBLE
        }
    }


    private fun sclick(viewpagermodel: viewpagermodel) {
         startActivity(
             Intent(activity, boookkk::class.java).putExtra(
                 "bookname",
                 viewpagermodel.vname

             )

         )
        UnityAds.removeListener(abc)
    }

    private fun clickkkk(viewpagermodel: viewpagermodel) {

    }


    private fun clickkk(viewpagermodel: viewpagermodel) {
        val k = viewpagermodel.vname
        val j = viewpagermodel.b1
        startActivity(
            Intent(activity, chapter::class.java).putExtra("hii", k).putExtra("hi", j).putExtra(
                "image",
                viewpagermodel.banner
            )
        )

UnityAds.removeListener(abc)
    }

    override fun onDestroy() {
        super.onDestroy()
        UnityAds.removeListener(abc)
    }



    override fun onResume() {

        super.onResume()

        if (chapter.coins>=100)
        {
            textt.text = "Enjoy your chapter"
            textt.setTextColor(Color.parseColor("#4BB543"))
        }



       val ggg = Prefs.getInt("coin", 0)
         text.text = ggg.toString()
        UnityAds.setListener(abc)
    }



}




