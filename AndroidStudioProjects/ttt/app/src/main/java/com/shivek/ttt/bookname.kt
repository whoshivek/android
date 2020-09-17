package com.shivek.ttt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.firebase.firestore.FirebaseFirestore
import com.unity3d.ads.UnityAds
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_bookname.*
import kotlinx.android.synthetic.main.fragment_bookname.view.*


class bookname : Fragment() {

    private val unityGameID = "3783445"

    private var bannerPlacement = "banner"
    private lateinit var bottomBanner : BannerView
    private val testMode = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_bookname, container, false)

         UnityAds.initialize(activity, unityGameID,  testMode);


        bottomBanner = BannerView(activity, bannerPlacement, UnityBannerSize(728, 90))
        v.banner4.addView(bottomBanner)
        bottomBanner.load()

        val c = activity?.intent?.getStringExtra("bookname")



        if (c != null) {
            FirebaseFirestore.getInstance().collection("lastadapter").document(c).get()
                .addOnCompleteListener {
                    if (it.isSuccessful)
                    {


                            v.description.text = it.result.get("comicdescription") as String
                        v.comicname.text = it.result.get("comicname") as String
                        v.genre.text = it.result.get("genre") as String

                    }
                }
        }
        return v
    }


}