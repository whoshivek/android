package com.shivek.pubgg

import android.app.DownloadManager
import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.widget.Toast
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)

        val ad = AdView(this)
        ad.adUnitId = "ca-app-pub-8822526167094562/3735023540"
        ad.adSize = AdSize.BANNER
        bb.setOnClickListener {
            download()
        }
        bbb.setOnClickListener {
            deleteex()
        }


    }

    private fun deleteex() {
        val p = Environment.getExternalStorageDirectory().toString()
        val f = File(p + "/Pictures/PicsArt/abc.jpg")
        if (f.isFile) {
            f.delete();
            if (f.exists()) {
                f.getCanonicalFile().delete();
                if (f.exists()) {
                    getApplicationContext().deleteFile(f.getName());
                }
            } else {
                Toast.makeText(this, "not a file", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun download() {
        val user = FirebaseStorage.getInstance().getReference()

        user.child("UIElemLayout_Slot.sav").downloadUrl.addOnCompleteListener {
            if (it.isSuccessful)
            {

                downloadfiles(it.result.toString(), "UIElemLayjjjout_Slot",
                        "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames/",
                        this, ".sav")
            }
        }
    }



    private fun downloadfiles(url : String, filename : String, destination : String, context: Context, ex : String) {
             val download = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
       // request.setDestinationInExternalFilesDir(context,destination,filename + ex)

        request.setDestinationInExternalPublicDir(destination, filename + ex)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        download.enqueue(request)
    }
}