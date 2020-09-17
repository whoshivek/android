package com.shivek.ttt

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.google.firebase.firestore.FirebaseFirestore
import com.shivek.mymallfinal.adapterandmodels.categorymodel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_addcoin.view.*
import kotlinx.android.synthetic.main.fragment_cccc.*
import kotlinx.android.synthetic.main.fragment_cccc.view.*


class cccc(titlee : String?) : Fragment() {
    val list = arrayListOf<categorymodel>()
      val ti = titlee
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_cccc, container, false)

      //  val title = activity?.intent?.getStringExtra("hi")

        val chapter = activity?.intent?.getStringExtra("hii")
      //  v.chaptername.text = chapter
        val dialog = ACProgressFlower.Builder(activity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .text("Loading")
            .fadeColor(Color.DKGRAY).build()
        dialog.show()
        if (chapter != null) {
            FirebaseFirestore.getInstance().collection("chapteraddapter").document(chapter).get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                      val  loadurl = it.result?.get("${ti}url") as String?
                             if (loadurl?.isNotBlank()!!)
                             {
                             v.wwee?.settings?.displayZoomControls= false
                                 v.wwee?.settings?.builtInZoomControls = true
                             v.wwee?.clearCache(true)
                                 v.wwee?.clearFormData()
                                 v.wwee?.settings?.javaScriptEnabled = true
                                 v.wwee?.webChromeClient = WebChromeClient()
                                 v.wwee?.settings?.loadWithOverviewMode = true
                                  v.wwee?.settings?.useWideViewPort = true
                                 v.wwee?.loadUrl(it.result.get("${ti}url") as String)
                                 v.wwee?.setWebViewClient(object : WebViewClient() {

                                     override fun onLoadResource(view: WebView, url: String) {
                                         super.onLoadResource(view, url)
                                         v.wwee?.loadUrl(
                                             """javascript:(window.onload = function() {  var elements = document.getElementsByClassName("entry-header header");
            while(elements.length > 0){
                elements[0].parentNode.removeChild(elements[0]);
            }
  var elementss = document.getElementsByClassName("main-navigation style-1");
            while(elementss.length > 0){
                elementss[0].parentNode.removeChild(elementss[0]);
            }
            
                var elementsss = document.getElementsByClassName("entry-header footer");
            while(elementsss.length > 0){
                elementsss[0].parentNode.removeChild(elementsss[0]);
            }
            
              var elementssss = document.getElementsByClassName("main-col col-12 col-sm-8 col-md-8 col-lg-8");
            while(elementssss.length > 0){
                elementssss[0].parentNode.removeChild(elementssss[0]);
            }
            
               var elementsssss = document.getElementsByClassName("widget col-12 col-md-12 bordered no-icon heading-style-2 manga-history-widget");
            while(elementsssss.length > 0){
                elementsssss[0].parentNode.removeChild(elementsssss[0]);
            }
       var e = document.getElementsByClassName("site-footer");
            while(e.length > 0){
                e[0].parentNode.removeChild(e[0]);
            }
             var eb = document.getElementsByClassName("wp-manga-tags");
            while(eb.length > 0){
                eb[0].parentNode.removeChild(eb[0]);
            }
             var a = document.getElementsByClassName("c-sub-nav_wrap");
            while(a.length > 0){
                a[0].parentNode.removeChild(a[0]);
            }   
            
             var b = document.getElementsByClassName("go-to-top active");
            while(b.length > 0){
                b[0].parentNode.removeChild(b[0]);
            } 
            
               var bepositive = document.getElementsByClassName("cookie-notice-container");
            while(bepositive.length > 0){
                bepositive[0].parentNode.removeChild(bepositive[0]);
            }   
            var myobj = document.getElementById("chapter-heading");
  myobj.remove();  
               
                  

})()"""
                                         )


                                     }


                                     override fun onPageStarted(
                                         view: WebView?,
                                         url: String?,
                                         favicon: Bitmap?
                                     ) {
                                         super.onPageStarted(view, url,null)
                                         dialog.dismiss()
                                         v.wwee?.visibility = View.VISIBLE
                                     }

                                     override fun onReceivedError(
                                         view: WebView?,
                                         errorCode: Int,
                                         description: String?,
                                         failingUrl: String?
                                     ) {
                                         super.onReceivedError(
                                             view,
                                             errorCode,
                                             description,
                                             failingUrl
                                         )
                                         v.wwee?.loadData("<!DOCTYPE html>\n" +
                                                 "<html>\n" +
                                                 "<head>\n" +
                                                 "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                                 "</head>\n" +
                                                 "<body>\n" +
                                                 "\n" +
                                                 "<h2>Kindly check your internet connection</h2>\n" +

                                                 "\n" +
                                                 "\n" +
                                                 "</body>\n" +
                                                 "</html>\n","text/html","UTF-8")
                                     }

                                 })






                             }

                       else if (loadurl.isBlank())
                        {
                            val loaddata = it.result.get("${ti}html") as String

                            if (loaddata.isNotBlank()) {

                                wwee.visibility = View.VISIBLE
                                dialog.dismiss()
                                wwee.loadData(loaddata,"text/html","UTF-8")
                                wwee.settings.builtInZoomControls = true
                                wwee.settings.displayZoomControls = false
                                wwee?.settings?.javaScriptEnabled = true
                                wwee?.clearCache(true)
                                wwee?.clearFormData()
                                wwee?.webViewClient = WebViewClient()
                            }
                            else{

                                wwee.visibility = View.VISIBLE
                                dialog.dismiss()
                                wwee?.loadData("<!DOCTYPE html>\n" +
                                        "<html>\n" +
                                        "<head>\n" +
                                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                        "</head>\n" +
                                        "<body>\n" +
                                        "\n" +
                                        "<h2>Chapter not found</h2>\n" +
                                        "<h2>We will upload it soon</h2>\n" +
                                        "\n" +
                                        "\n" +
                                        "</body>\n" +
                                        "</html>\n","text/html","UTF-8")

                                wwee?.settings?.javaScriptEnabled = true
                                wwee?.clearCache(true)
                                wwee?.clearFormData()
                                wwee?.webViewClient = WebViewClient()
                            }
                        }

                    }
                    else {
                        activity?.let { it1 -> it.exception?.message?.let { it2 ->
                            Toasty.error(it1,
                                it2, Toasty.LENGTH_LONG).show()
                        } }
                    }
                }
        }








        return v

    }

}