package com.manish.wwww

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dialog = ACProgressFlower.Builder(this)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .text("Loading ")
            .fadeColor(Color.DKGRAY).build()
        dialog.show()
webb.settings.builtInZoomControls = true
        webb.settings.displayZoomControls = false
        webb.settings.javaScriptEnabled = true
webb.webChromeClient = WebChromeClient()
        webb.settings.loadWithOverviewMode = true

        webb.setWebViewClient(object : WebViewClient() {




            override fun onLoadResource(view: WebView, url: String) {
                super.onLoadResource(view, url)
                webb.loadUrl(
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

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                dialog.dismiss()
                webb.visibility = View.VISIBLE
            }

        })

webb.loadUrl("https://twilightscans.com/manga/spicy-female-boss/chapter-12/")

    }
}