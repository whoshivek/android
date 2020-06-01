package com.shivek.pdf

import android.app.DownloadManager
import android.content.Context
import android.content.res.AssetManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.os.FileUtils
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.github.barteksc.pdfviewer.PDFView

import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.net.URI


var file = File("abc",".pdf")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            download()
        }



        val file = this.getExternalFilesDir(DIRECTORY_DOWNLOADS + "/abc.pdf")




    }

    private fun download() {
        val storge = FirebaseStorage.getInstance().getReference()
        val ONE_MEGABYTE: Long = 1024 * 1024
        val f = File.createTempFile("abc","pdf")


        storge.child("12_sp_english_core_cbse.pdf").downloadUrl.addOnCompleteListener{
            if (it.isSuccessful)
            {



               downloadfiles(it.result.toString(),"abcd",DIRECTORY_DOWNLOADS,this , ".pdf")
            }
        }

    }

    private fun downloadfiles( url : String ,  filename : String ,destination : String , context: Context ,ex : String) {
        val download =  context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalFilesDir(context, destination, filename + ex)
        val file = this.getExternalFilesDir(DIRECTORY_DOWNLOADS + "/${filename}${ex}")
        download.enqueue(request)



        if (file != null) {
            if (file.exists())
            {

                pdf.fromFile(file)
                    .load()
            }


        }
    }


}